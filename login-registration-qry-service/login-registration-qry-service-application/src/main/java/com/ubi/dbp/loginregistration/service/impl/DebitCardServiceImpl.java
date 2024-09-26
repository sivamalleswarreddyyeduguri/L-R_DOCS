package com.ubi.dbp.loginregistration.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubi.dbp.loginregistration.config.URLConfiguration;
import com.ubi.dbp.loginregistration.constants.LoginRegistrationConstants;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.model.*;
import com.ubi.dbp.loginregistration.service.DebitCardService;
import com.ubi.dbp.loginregistration.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {
	private URLConfiguration urlConfiguration;

	private WebClient.Builder webClient;


	@Override
	public Mono<VerifyDebitCardResp> verifyDebitCard(VerifyDebitCard verifyDebitCard) {
		String url = AppUtils.buildUrl(urlConfiguration.getAtmBaseURL(), urlConfiguration.getVerifyDebit());
		return webClient.build().post().uri(url).body(BodyInserters.fromValue(verifyDebitCard))
				.retrieve()
				.bodyToMono(String.class).flatMap(response -> {
					try {
						JsonNode isSuccess = new ObjectMapper().readTree(response);
						if (isSuccess.path(LoginRegistrationConstants.SUCCESS).asBoolean()){
							VerifyDebitCardResp verifyDebitCardResp= new VerifyDebitCardResp(isSuccess.path(LoginRegistrationConstants.TRANS_REF_NO).asText(),true,LoginRegistrationConstants.DEBIT_VERIFIED);
							return Mono.just(verifyDebitCardResp);
						}else {
							JsonNode errorNode = new ObjectMapper().readTree(response).path(LoginRegistrationConstants.ERROR_MSG);
							String errorCode = errorNode.path(LoginRegistrationConstants.CODE).asText();
							return Mono.error(new LoginRegistrationException(ErrorConst.fromCode(errorCode)));
						}
					} catch (Exception e) {
						return Mono.error(new LoginRegistrationException(ErrorConst.UNABLE_TO_PROCESS));
					}
				});
		}
}
