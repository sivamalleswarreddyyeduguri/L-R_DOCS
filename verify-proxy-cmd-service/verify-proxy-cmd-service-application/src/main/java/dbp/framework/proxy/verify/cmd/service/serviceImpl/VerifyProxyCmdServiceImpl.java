package dbp.framework.proxy.verify.cmd.service.serviceImpl;

import dbp.framework.proxy.verify.cmd.service.config.VerifyProxyConfiguration;
import dbp.framework.proxy.verify.cmd.service.exception.VerifyException;
import dbp.framework.proxy.verify.cmd.service.model.GenericException;
import dbp.framework.proxy.verify.cmd.service.model.GenericResponse;
import dbp.framework.proxy.verify.cmd.service.model.SetMpinReq;
import dbp.framework.proxy.verify.cmd.service.model.SetTPinReq;
import dbp.framework.proxy.verify.cmd.service.model.corpregistration.CorpUserRegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.registration.RegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.securityque.SetSecurityQuestion;
import dbp.framework.proxy.verify.cmd.service.model.setpassword.SetPasswordReq;
import dbp.framework.proxy.verify.cmd.service.service.VerifyProxyCmdService;
import dbp.framework.proxy.verify.cmd.service.util.AppUtils;
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
public class VerifyProxyCmdServiceImpl implements VerifyProxyCmdService {

    private final VerifyProxyConfiguration verifyProxyConfiguration;
    private final WebClient.Builder webClient;


    @Override
    public Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq) {
        return webClient.build().post()
                .uri(AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getSetPasswordEndpoint()))
                .bodyValue(setPasswordReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion) {
        return webClient.build().post()
                .uri(AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getSecurityQuestionEndpoint()))
                .bodyValue(setSecurityQuestion)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> registerCorpUser(CorpUserRegistrationReq corpUserRegistrationReq) {
        return webClient.build().post()
                .uri(AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getCorpUserRegistrationEndpoint()))
                .bodyValue(corpUserRegistrationReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq) {
        String url = AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getSetMPinEndpoint());
        return webClient.build().post()
                .uri(url)
                .bodyValue(setMpinReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }


    @Override
    public Mono<GenericResponse> setTpin(SetTPinReq setTPinReq) {
        String url = AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getSetTpinEndpoint());
        return webClient.build().post()
                .uri(url)
                .bodyValue(setTPinReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> mbRegistration(RegistrationReq registrationReq) {
        String url = AppUtils.buildUrl(verifyProxyConfiguration.getBaseUrl(), verifyProxyConfiguration.getRegistrationEndpoint());
        return webClient.build().post()
                .uri(url)
                .bodyValue(registrationReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, VerifyProxyCmdServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, VerifyProxyCmdServiceImpl::handleClientResponse)
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


