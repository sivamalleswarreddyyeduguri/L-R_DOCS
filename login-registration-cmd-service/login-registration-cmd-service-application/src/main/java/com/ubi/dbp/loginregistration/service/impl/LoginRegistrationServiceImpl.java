package com.ubi.dbp.loginregistration.service.impl;

import dbp.framework.support.exception.ErrorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.ubi.dbp.loginregistration.config.LoginRegistrationConfiguration;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import com.ubi.dbp.loginregistration.model.*;
import com.ubi.dbp.loginregistration.service.LoginRegistrationService;
import com.ubi.dbp.loginregistration.utils.AppUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class LoginRegistrationServiceImpl implements LoginRegistrationService {


    private final LoginRegistrationConfiguration loginRegistrationConfiguration;

    private final WebClient.Builder webclient;

    @Override
    public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq) {
        String url = AppUtils.buildUrl(loginRegistrationConfiguration.getBaseUrl(), loginRegistrationConfiguration.getSetMpinEndpoint());
        log.info("Constructed URL: {}", url);
        return webclient.build().post()
                .uri(url)
                .bodyValue(setMpinReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, LoginRegistrationServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, LoginRegistrationServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> setTPin(SetTPinReq setTPinReq) {
        String url = AppUtils.buildUrl(loginRegistrationConfiguration.getBaseUrl(), loginRegistrationConfiguration.getSetTpinEndpoint());
        log.info("Constructed URL: {}", url);
        return webclient.build().post()
                .uri(url)
                .bodyValue(setTPinReq)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, LoginRegistrationServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, LoginRegistrationServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }


    @Override
    public Mono<GenericResponse> mbRegistration(MbRegistrationReq userReq) {
        String url = AppUtils.buildUrl(loginRegistrationConfiguration.getBaseUrl(),
                loginRegistrationConfiguration.getMbRegistration());

        log.info("Constructed URL: {}", url);

        return webclient.build().post().uri(url).bodyValue(userReq).retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, LoginRegistrationServiceImpl::handleClientResponse)
                .onStatus(HttpStatusCode::is5xxServerError, LoginRegistrationServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);

    }

    @Override
    public Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion) {

        String url = AppUtils.buildUrl(loginRegistrationConfiguration.getBaseUrl(),
                loginRegistrationConfiguration.getSecurityQuestions());

        log.info("Constructed URL: {}", url);

        return webclient.build().post().uri(url).bodyValue(setSecurityQuestion).retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        LoginRegistrationServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }

    @Override
    public Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq) {
        String url = AppUtils.buildUrl(loginRegistrationConfiguration.getBaseUrl(),
                loginRegistrationConfiguration.getPassword());

        log.info("Constructed URL: {}", url);

        return webclient.build().post().uri(url).bodyValue(setPasswordReq).retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        LoginRegistrationServiceImpl::handleClientResponse)
                .bodyToMono(GenericResponse.class);
    }


    public static Mono<? extends Throwable> handleClientResponse(ClientResponse clientResponse) {

        return clientResponse.bodyToMono(GenericException.class).flatMap(responseMessage ->
                Mono.error(new LoginRegistrationException(
                        responseMessage.getError_code(), responseMessage.getErrordesc())
                )
        );
    }
}