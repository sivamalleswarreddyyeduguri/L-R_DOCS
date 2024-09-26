package dbp.framework.proxy.verify.cmd.service.service;

import dbp.framework.proxy.verify.cmd.service.model.GenericResponse;
import dbp.framework.proxy.verify.cmd.service.model.SetMpinReq;
import dbp.framework.proxy.verify.cmd.service.model.SetTPinReq;
import dbp.framework.proxy.verify.cmd.service.model.corpregistration.CorpUserRegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.registration.RegistrationReq;
import dbp.framework.proxy.verify.cmd.service.model.securityque.SetSecurityQuestion;
import dbp.framework.proxy.verify.cmd.service.model.setpassword.SetPasswordReq;
import reactor.core.publisher.Mono;

public interface VerifyProxyCmdService {

    public Mono<GenericResponse> mbRegistration(RegistrationReq registrationReq);
    public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq);

    public Mono<GenericResponse> setTpin(SetTPinReq setTPinReq);
    public Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq);
    public Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion);
    public Mono<GenericResponse> registerCorpUser(CorpUserRegistrationReq corpUserRegistrationReq);

}
