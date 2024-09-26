package dbp.framework.proxy.verify.qry.service.service.impl;

import com.ubi.dbp.proxy.service.model.*;
import dbp.framework.proxy.verify.qry.service.config.VerifyProxyQryConfiguration;
import dbp.framework.proxy.verify.qry.service.exception.VerifyException;
import dbp.framework.proxy.verify.qry.service.service.VerifyProxyQryService;
import dbp.framework.proxy.verify.qry.service.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@AllArgsConstructor
@Service
public class VerifyProxyQryServiceImpl implements VerifyProxyQryService {

    private final WebClient.Builder webClient;
    private final VerifyProxyQryConfiguration verifyProxyQryConfiguration;


    @Override
    public Mono<GenerateOtpResp> generateOtp(GenerateOtpReq generateOtpReq) {

        String url = AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getGenerateOtpEndpoint());
        log.info("Constructed URL: {}", url);

        return webClient.build()
                .post()
                .uri(url)
                .bodyValue(generateOtpReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(GenerateOtpResp.class);
    }

    @Override
    public Mono<VerifyOtpResp> verifyOtp(VerifyOtpReq verifyOtpReq) {
        verifyOtpReq.setOtphint(verifyOtpReq.getOtphint());

        String url = AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getVerifyOtpEndpoint());
        log.info("Constructed URL: {}", url);

        return webClient.build()
                .post()
                .uri(url)
                .bodyValue(verifyOtpReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(VerifyOtpResp.class);
    }

    @Override
    public Mono<CorpLoginResp> corpLogin(CorpLoginReq corpLoginReq) {
        return webClient.build()
                .post()
                .uri(AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getCorpUserLoginEndpoint()))
                .bodyValue(corpLoginReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(CorpLoginResp.class);
    }

    @Override
    public Mono<SecurityQuestions> getQuestionByCif(SecurityQuestionReq securityQuestionReq) {
        return webClient.build()
                .post()
                .uri(AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getGetSecurityQuestionByCif()))
                .bodyValue(securityQuestionReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(SecurityQuestions.class);
    }

    @Override
    public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions() {
        return webClient.build()
                .get()
                .uri(AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getKnowledgeQuestions()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(KnowledgeQuestionsResp.class);
    }

    @Override
    public Mono<ValidateKnowledgeQuestionsResp> validateUserKnowledgeQuestions(ValidateKnowledgeQuestionsReq validateKnowledgeQuestionsReq) {
        return webClient.build()
                .post()
                .uri(AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getValidateKnowledgeQuestions()))
                .bodyValue(validateKnowledgeQuestionsReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(ValidateKnowledgeQuestionsResp.class);
    }

    @Override
    public Mono<GenericResponse> verifyOLdLoginMPin(VerifyOldLoginMPin verifyOldLoginMPin) {
        String url = AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getVerifyOldLoginMpinEndpoint());
        return webClient.build().post()
                .uri(url)
                .bodyValue(verifyOldLoginMPin)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq) {
        String url = AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getCheckMbRegistration());
        return webClient.build().post()
                .uri(url)
                .bodyValue(checkMbRegistrationReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(CheckMbRegistrationResp.class);
    }

    @Override
    public Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin) {
        String url = AppUtils.buildUrl(verifyProxyQryConfiguration.getBaseUrl(), verifyProxyQryConfiguration.getVerifyTpinEndpoint());
        return webClient.build().post()
                .uri(url)
                .bodyValue(verifyTpin)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyQryServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyQryServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }


    public static Mono<? extends Throwable> handleClientResponse(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(GenericException.class).flatMap(responseMessage ->
                Mono.error(new VerifyException(
                        responseMessage.getErrordesc(),
                        responseMessage.getError_code(),
                        clientResponse.statusCode().value())
                )
        );
    }
}

