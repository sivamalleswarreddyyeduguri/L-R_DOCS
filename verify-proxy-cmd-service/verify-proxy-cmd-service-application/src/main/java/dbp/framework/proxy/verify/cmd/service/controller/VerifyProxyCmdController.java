package dbp.framework.proxy.verify.cmd.service.controller;

import dbp.framework.proxy.verify.cmd.service.model.GenericResponse;
import dbp.framework.proxy.verify.cmd.service.model.SetMpinReq;
import dbp.framework.proxy.verify.cmd.service.model.SetTPinReq;
import dbp.framework.proxy.verify.cmd.service.model.corpregistration.CorpUserRegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.registration.RegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.securityque.SetSecurityQuestion;
import dbp.framework.proxy.verify.cmd.service.model.setpassword.SetPasswordReq;
import dbp.framework.proxy.verify.cmd.service.service.VerifyProxyCmdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/verify-proxy/api/v1/user")
public class VerifyProxyCmdController {

    private final VerifyProxyCmdService verifyProxyCmdService;

    @PostMapping(value = "/mbregistration", produces = "application/json")
    public Mono<GenericResponse> mbRegistration(RegistrationReq registrationReq) {

        return verifyProxyCmdService.mbRegistration(registrationReq);
    }

    @PostMapping(value = "/mpin", produces = "application/json")
    public Mono<GenericResponse> setMpin(@RequestBody SetMpinReq setMpinReq) {
        return verifyProxyCmdService.setMpin(setMpinReq);
    }

    @PostMapping(value = "/tpin", produces = "application/json")
    public Mono<GenericResponse> setTpin(@RequestBody SetTPinReq setTPinReq) {
        return verifyProxyCmdService.setTpin(setTPinReq);
    }

    @PostMapping(value = "/password", produces = "application/json")
    public Mono<GenericResponse> setPassword(@RequestBody SetPasswordReq setPasswordReq) {
        return verifyProxyCmdService.setPassword(setPasswordReq);
    }

    @PostMapping(value = "/security-question", produces = "application/json")
    public Mono<GenericResponse> setSecurityQuestion(@RequestBody SetSecurityQuestion setSecurityQuestion) {
        return verifyProxyCmdService.setSecurityQuestion(setSecurityQuestion);
    }

    @PostMapping(value = "/corp/registration", produces = "application/json")
    public Mono<GenericResponse> registerCorpUser(@RequestBody CorpUserRegistrationReq corpUserRegistrationReq) {
        return verifyProxyCmdService.registerCorpUser(corpUserRegistrationReq);
    }

}