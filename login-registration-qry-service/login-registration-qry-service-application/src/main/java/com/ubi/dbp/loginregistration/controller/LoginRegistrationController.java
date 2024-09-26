package com.ubi.dbp.loginregistration.controller;

import com.ubi.dbp.loginregistration.model.*;
import com.ubi.dbp.loginregistration.service.DebitCardService;
import com.ubi.dbp.loginregistration.service.IbmVerifyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.GenerateAadharOtp;
import com.ubi.dbp.loginregistration.model.GenerateAadharOtpResp;
import com.ubi.dbp.loginregistration.model.VerifyAadharOtp;
import com.ubi.dbp.loginregistration.model.VerifyAadharOtpResp;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import com.ubi.dbp.loginregistration.service.AadharService;
import com.ubi.dbp.loginregistration.service.LoginRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/login-registration/api/v1")
@AllArgsConstructor
public class LoginRegistrationController {


    private LoginRegistrationService loginRegistrationService;
    private AadharService aadharService;
    private DebitCardService debitCardService;
    private IbmVerifyService ibmVerifyService;

    @PostMapping(value = "/register/verify-mobile")
    public Mono<VerifyMobileResp> checkExistingUser(@RequestBody CheckMbRegistrationReq mobile) {
        return loginRegistrationService.checkExistingUser(mobile);

    }

    @PostMapping(value = "/checkMbRegistration", produces = "application/json")
    public Mono<CheckMbRegistrationResp> checkMbRegistration(@RequestBody CheckMbRegistrationReq checkMbRegistrationReq) {
        return ibmVerifyService.checkMbRegistration(checkMbRegistrationReq);
    }

    @PostMapping(value = "/generate-otp")
    public Mono<GenerateAadharOtpResp> generateAadharOTP(@RequestBody GenerateAadharOtp generateAadharOtpReq) {
        return aadharService.generateAadharOtp(generateAadharOtpReq);
    }


    @PostMapping(value = "/validate-otp")
    public Mono<VerifyAadharOtpResp> verifyAadharOTP(@RequestBody VerifyAadharOtp verifyAadharOtpReq) {
        return aadharService.verifyAadharOtp(verifyAadharOtpReq);
    }

    @PostMapping(value = "/verifyDebitCard")
    public Mono<VerifyDebitCardResp> verifyDebitCard(@RequestBody VerifyDebitCard verifyDebitCard) {
        return debitCardService.verifyDebitCard(verifyDebitCard);
    }

    @PostMapping(value = "/corpLogin", produces = "application/json")
    public Mono<CorpLoginResp> corpLogin(@RequestBody CoprpLoginReq CoprpLoginReq) {
        return ibmVerifyService.corpLogin(CoprpLoginReq);
    }

    @PostMapping(value = "/get-security-questions", produces = "application/json")
    public Mono<SecurityQuestions> getSecurityQuestionsByCif(@RequestBody GetSecurityQuestionReq securityQuestionReq) {
        return ibmVerifyService.getQuestionsByCif(securityQuestionReq);
    }


    @PostMapping(value = "/generateotp", produces = "application/json")
    public Mono<GenerateOtpResp> generateOtp(@RequestBody GenerateOtpReq generateOtpReq) {
        return ibmVerifyService.generateOtp(generateOtpReq);
    }

    @PostMapping(value = "/verifyotp", produces = "application/json")
    public Mono<VerifyOtpResp> verifyOtp(@RequestBody VerifyOtpReq verifyOtpReq) {
        return ibmVerifyService.verifyOtp(verifyOtpReq);

    }

    @PostMapping(value = "/get-all-knowledge-questions")
    public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions() {
        return ibmVerifyService.getAllKnowledgeQuestions();
    }

    @PostMapping(value = "/verifyoldloginpin", produces = "application/json")
    public Mono<GenericResponse> verifyOldloginMpin(@RequestBody VerifyOldLoginMPin verifyOldLoginMPin) {
        return ibmVerifyService.verifyOldloginMpin(verifyOldLoginMPin);
    }

    @PostMapping(value = "/verifytpin", produces = "application/json")
    public Mono<GenericResponse> verifyTpin(@RequestBody VerifyTpin verifyTpin) {
        return ibmVerifyService.verifyTpin(verifyTpin);
    }
}
 