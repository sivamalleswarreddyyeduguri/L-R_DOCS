package com.ubi.dbp.loginregistration.service.impl;

import com.ubi.dbp.loginregistration.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import com.ubi.dbp.loginregistration.config.URLConfiguration;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationResp;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.service.IbmVerifyService;
import com.ubi.dbp.loginregistration.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class IbmVerifyServiceImpl implements IbmVerifyService {

	private WebClient.Builder webClient;
	private URLConfiguration urlConfiguration;

	@Override
	public Mono<SecurityQuestions> getQuestionsByCif(GetSecurityQuestionReq securityQuestionReq) {
		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(),
				urlConfiguration.getSecurityQuestions());

		log.info("Constructed URL: {}", url);

		return webClient.build().post().uri(url).bodyValue(securityQuestionReq).retrieve()
				.onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
						IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(SecurityQuestions.class);
	}

	@Override
	public Mono<CorpLoginResp> corpLogin(CoprpLoginReq coprpLoginReq) {
		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(),
				urlConfiguration.getCorpUserLogin());

		log.info("Constructed URL: {}", url);

		return webClient.build().post().uri(url).bodyValue(coprpLoginReq).retrieve()
				.onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
						IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(CorpLoginResp.class);
	}

	@Override
	public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions() {
		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(),
				urlConfiguration.getKnowledgeQuestions());

		log.info("Constructed URL: {}", url);

		return webClient.build().post().uri(url).retrieve()
				.onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
						IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(KnowledgeQuestionsResp.class);
	}


	@Override
	public Mono<GenerateOtpResp> generateOtp(GenerateOtpReq generateOtpReq) {


		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(), urlConfiguration.getGenerateOtpEndpoint());
		log.info("build url {}:", url);
		return webClient.build().post()
				.uri(url)
				.bodyValue(generateOtpReq)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, IbmVerifyServiceImpl::handleClientResponse)
				.onStatus(HttpStatusCode::is5xxServerError, IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(GenerateOtpResp.class);
	}


	@Override
	public Mono<VerifyOtpResp> verifyOtp(VerifyOtpReq verifyOtpReq) {

		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(), urlConfiguration.getVerifyOtpEndpoint());

		return webClient.build().post()
				.uri(url)
				.bodyValue(verifyOtpReq)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, IbmVerifyServiceImpl::handleClientResponse)
				.onStatus(HttpStatusCode::is5xxServerError, IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(VerifyOtpResp.class);


	}
	@Override
	public Mono<GenericResponse> verifyOldloginMpin(VerifyOldLoginMPin verifyOldLoginMPin){

		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(), urlConfiguration.getVerifyOldLoginMpinEndpoint());

		return webClient.build().post()
				.uri(url)
				.bodyValue(verifyOldLoginMPin)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, IbmVerifyServiceImpl::handleClientResponse)
				.onStatus(HttpStatusCode::is5xxServerError, IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(GenericResponse.class);


	}

	@Override
	public Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq) {
		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(), urlConfiguration.getCheckMbRegistration());

		return webClient.build().post()
				.uri(url)
				.bodyValue(checkMbRegistrationReq)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, IbmVerifyServiceImpl::handleClientResponse)
				.onStatus(HttpStatusCode::is5xxServerError, IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(CheckMbRegistrationResp.class);
	}

	@Override
	public Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin) {
		String url = AppUtils.buildUrl(urlConfiguration.getBaseUrl(), urlConfiguration.getVerifyTpinEndpoint());

		return webClient.build().post()
				.uri(url)
				.bodyValue(verifyTpin)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, IbmVerifyServiceImpl::handleClientResponse)
				.onStatus(HttpStatusCode::is5xxServerError, IbmVerifyServiceImpl::handleClientResponse)
				.bodyToMono(GenericResponse.class);
	}

	public static Mono<? extends Throwable> handleClientResponse(ClientResponse clientResponse) {

		return clientResponse.bodyToMono(GenericException.class).flatMap(responseMessage ->
				Mono.error(new LoginRegistrationException(
						responseMessage.getError_code(),responseMessage.getErrordesc())
				)
		);
	}

}
 