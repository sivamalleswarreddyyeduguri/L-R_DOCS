package com.ubi.dbp.loginregistration.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.ubi.dbp.loginregistration.config.URLConfiguration;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.model.AccountTypeReq;
import com.ubi.dbp.loginregistration.model.AccountTypeResponse;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.CustAcctView;
import com.ubi.dbp.loginregistration.model.CustomerByCifReq;
import com.ubi.dbp.loginregistration.model.CustomerInfo;
import com.ubi.dbp.loginregistration.model.CustomerInqByMobResp;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import com.ubi.dbp.loginregistration.service.CustomerService;
import com.ubi.dbp.loginregistration.utils.AppUtils;

import dbp.framework.common.model.Account;
import dbp.framework.common.model.Customer;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private WebClient.Builder webClient;
	private URLConfiguration urlConfiguration;

	public CustomerServiceImpl(Builder webClient, URLConfiguration urlConfiguration) {
		this.webClient = webClient;
		this.urlConfiguration = urlConfiguration;
	}

	
	@Override
	public Mono<VerifyMobileResp> customerInquiryByMobile(CheckMbRegistrationReq mobile) {
	    log.info("Inside customerInquiryByMobile method()");

	    String url = AppUtils.buildUrl(urlConfiguration.getCustomerBaseURL(),
	            urlConfiguration.getCustInqByMob() + mobile.getMobile());
	    log.info("Constructed URL: {}", url);
  
	    return webClient.build()
	            .get()
	            .uri(url)
	            .retrieve()
	            .bodyToMono(CustomerInqByMobResp.class)
	            .flatMap(response -> {
	                List<CustAcctView> customerList = response.getCustAcctView();
	                
	                log.info("Customer List: {}", customerList);

	                Set<String> uniqueCifs = customerList.stream()
	                        .map(CustAcctView::getCif)
	                        .collect(Collectors.toSet());

	                List<CustomerByCifReq> cifs = uniqueCifs.stream()
	                        .map(CustomerByCifReq::new)
	                        .collect(Collectors.toList());

	                return Mono.just(new VerifyMobileResp(cifs));
	                
	            })
	            .onErrorResume(throwable -> {
	            	
	                if (throwable instanceof WebClientResponseException) {
	                    WebClientResponseException webClientException = (WebClientResponseException) throwable;

	                    HttpStatusCode statusCode = webClientException.getStatusCode();
	                    log.error("Status Code: {}", statusCode);

	                    if (statusCode == HttpStatus.NOT_FOUND) { 
	                        VerifyMobileResp errorResponse = new VerifyMobileResp();
	                        errorResponse.setRespcode(String.valueOf(statusCode.value()));
	                        
	                        return Mono.just(errorResponse);
	                        
	                    }
	                }

	                return Mono.error(throwable);
	                
	            });
	    
	     }
	
	
 

	@Override
	public Mono<List<CustomerInfo>> getCustomerAccountsByCif(List<CustomerByCifReq> cifList) {
		log.info("Inside getCustomerAccountsByCif method()");
		log.info("++++++++++++++++++++" + cifList);
		String url = AppUtils.buildUrl(urlConfiguration.getCustomerBaseURL(),
				urlConfiguration.getGetCustomerAcctsByCIF());
		log.info("Constructed URL: {}", url);
		
		return fetchAccountTypes()
				.collectList()
				.flatMap(fetchAccountTypes -> {
					List<String> acctTypes = fetchAccountTypes.stream()
							.flatMap(obj -> Stream.of(obj.getEqualValue().split(","))).collect(Collectors.toList());
					
					System.out.println(acctTypes);

					
					CustomerByCifReq firstCif = null;
					
					if (!CollectionUtils.isEmpty(cifList)) {
						firstCif = cifList.get(0);
						System.out.println(firstCif);
					}
					
					return webClient.build().post()
							.uri(url)
							.body(BodyInserters.fromValue(firstCif))
							.retrieve()
							.bodyToFlux(CustomerInfo.class)
							.filter(customerInfo -> acctTypes.contains(customerInfo.getAcctType())
									&& urlConfiguration.getAcctStatus().contains(customerInfo.getAcctStatus()))
							.collectList().flatMap(customerInfos -> {
								if (!customerInfos.isEmpty()) {
									return Mono.just(customerInfos);
								} else {
									return Mono.empty();
								}
							});
				}).onErrorResume(error -> {
					log.error("Error occurred while fetching customer by CIF: {}", error.getMessage());
					return Mono.empty();  
				});
	   }

	public Flux<AccountTypeResponse> fetchAccountTypes() {
		log.info("inside fetchAccountTypes()");

		String url = AppUtils.buildUrl(urlConfiguration.getConfigServiceBaseURL(),
				urlConfiguration.getFetchBusinessRules());
		log.info("Constructed URL: {}", url);

		AccountTypeReq accountTypeReq = new AccountTypeReq();
		accountTypeReq.setSchemeCode("ALL");
		accountTypeReq.setSchemeSubTypeCode("LOGIN_REGISTRATION");
		accountTypeReq.setSubscriptionCode("LOGIN_REGISTRATION");
		accountTypeReq.setSchemeTypeCode("LOGIN_REGISTRATION");

		return webClient.build()
				.post()
				.uri(url)
				.body(BodyInserters.fromValue(accountTypeReq))
				.retrieve()
				.bodyToFlux(AccountTypeResponse.class);
		
	}	
	
	
	@Override
	public Mono<Customer> getCustomerByCif(String cif) {
	    log.info("inside getCustomerByCifCbs()");

	    String url = AppUtils.buildUrl(urlConfiguration.getCustomerBaseURL(),
	            urlConfiguration.getGetCustomerByCIF() + cif);
	    log.info("Constructed URL: {}", url);

	    return webClient.build()
	            .get()
	            .uri(url)
	            .retrieve()
	            .bodyToMono(Customer.class)
	            .doOnNext(response -> log.info("API Response: {}", response))
	            .onErrorMap(throwable->{
	            	throw new LoginRegistrationException(ErrorConst.NO_RECORD_FOUND);
	            });
	    
	  }

	
	
}
