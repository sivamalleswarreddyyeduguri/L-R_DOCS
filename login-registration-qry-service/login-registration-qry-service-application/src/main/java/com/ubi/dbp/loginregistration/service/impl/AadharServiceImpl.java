package com.ubi.dbp.loginregistration.service.impl;

import com.ubi.dbp.loginregistration.constants.LoginRegistrationConstants;
import com.ubi.dbp.loginregistration.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import com.ubi.dbp.loginregistration.config.URLConfiguration;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.service.AadharService;
import com.ubi.dbp.loginregistration.service.CustomerService;
import com.ubi.dbp.loginregistration.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class AadharServiceImpl implements AadharService {

    private CustomerService customerService;

    private URLConfiguration urlConfiguration;

    private WebClient.Builder webClient;

    @Override
    public Mono<GenerateAadharOtpResp> generateAadharOtp(GenerateAadharOtp otpReq) {
        String url = AppUtils.buildUrl(urlConfiguration.getAadhaarProxyBaseUrl(), urlConfiguration.getGenerateOtp());
        log.info("Constructed URL: {}", url);

        return customerService.getCustomerByCif(otpReq.getCif())
                .flatMap(response -> {
                    if (response.getKyc().getNationalId().equalsIgnoreCase(otpReq.getVid())) {
                        return webClient.build()
                                .post()
                                .uri(url)
                                .body(BodyInserters.fromValue(otpReq))
                                .retrieve()
                                .bodyToMono(GenerateAadharOtpResp.class)
                                .flatMap(generateAadharOtpResp -> {
                                    if (LoginRegistrationConstants.STATUS_CODE_SUCCESS.equals(generateAadharOtpResp.getRespCode())) {
                                        log.info("Aadhar OTP generated successfully for Aadhar number: {}", otpReq.getVid());
                                        return Mono.just(generateAadharOtpResp);
                                    } else if (LoginRegistrationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR.equals(generateAadharOtpResp.getRespCode())) {
                                        log.error("internal server error: {}", generateAadharOtpResp.getRespCode());
                                        return Mono.error(new LoginRegistrationException(ErrorConst.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
                                    } else if (LoginRegistrationConstants.STATUS_CODE_OTP_GENERATION_LIMIT.equals(generateAadharOtpResp.getRespCode())) {
                                        log.error("OTP generation limit: {}", generateAadharOtpResp.getRespCode());
                                        return Mono.error(new LoginRegistrationException(ErrorConst.OTP_GENERATION_LIMIT));
                                    } else {
                                        log.error("Failed to generate Aadhar OTP for Aadhar number: {}, Response Code: {}",
                                                otpReq.getVid(), generateAadharOtpResp.getRespCode());
                                        return Mono.error(new LoginRegistrationException(ErrorConst.AADHAR_VALIDATION_FAILED));
                                    }
                                });

                    } else {
                        log.error("No matching Aadhar number found for CIF: {} in CBS", otpReq.getCif());
                        return Mono.error(new LoginRegistrationException(ErrorConst.AADHAR_VALIDATION_FAILED));
                    }
                });

    }


    @Override
    public Mono<VerifyAadharOtpResp> verifyAadharOtp(VerifyAadharOtp verifyAadharOtpReq) {

        verifyAadharOtpReq.setTxn(LoginRegistrationConstants.TXN_PREFIX.concat(verifyAadharOtpReq.getTxn()));

        String url = AppUtils.buildUrl(urlConfiguration.getAadhaarProxyBaseUrl(), urlConfiguration.getValidateOtp());

        log.info("Constructed URL: {}", url);

        return webClient.build().post().uri(url).body(BodyInserters.fromValue(verifyAadharOtpReq)).retrieve()
                .bodyToMono(VerifyAadharOtpResp.class).flatMap(otpResp -> {
                    if (LoginRegistrationConstants.STATUS_CODE_SUCCESS.equals(otpResp.getRespCode())) {
                        log.info("Aadhar OTP validation result: {}", otpResp.getRespDesc());
                        return Mono.just(otpResp);
                    } else if (LoginRegistrationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR.equals(otpResp.getRespCode())) {
                        log.error("internal server error: {}", otpResp.getRespDesc());
                        return Mono.error(new LoginRegistrationException(ErrorConst.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
                    } else if (LoginRegistrationConstants.STATUS_CODE_TXN_MISMATCH.equals(otpResp.getRespCode())) {
                        log.error("txn mismatch: {}", otpResp.getRespDesc());
                        return Mono.error(new LoginRegistrationException(ErrorConst.TXN_MISMATCH));
                    } else if (LoginRegistrationConstants.STATUS_CODE_OTP_ATTEMPT_EXCEEDED.equals(otpResp.getRespCode())) {
                        log.error("otp attempt exceeded: {}", otpResp.getRespDesc());
                        return Mono.error(new LoginRegistrationException(ErrorConst.OTP_ATTEMPT_EXCEEDED));
                    } else {
                        log.error("Aadhar OTP validation result: {}", otpResp.getRespDesc());
                        return Mono.error(new LoginRegistrationException(ErrorConst.OTP_VALIDATION_FAILED));
                    }
                });

    }

}
