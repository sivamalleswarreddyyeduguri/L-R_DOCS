package com.ubi.dbp.loginregistration.service;

import java.util.List;

import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.CustomerByCifReq;
import com.ubi.dbp.loginregistration.model.CustomerInfo;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import dbp.framework.common.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
		
	Mono<VerifyMobileResp> customerInquiryByMobile(CheckMbRegistrationReq mobile); 
	Mono<List<CustomerInfo>> getCustomerAccountsByCif(List<CustomerByCifReq> cif);	
	Mono<Customer> getCustomerByCif(String cif);
	
}
