package dbp.framework.proxy.verify.qry.service.controller;

import com.ubi.dbp.proxy.service.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import dbp.framework.proxy.verify.qry.service.service.VerifyProxyQryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/verify-proxy/api/v1")
@AllArgsConstructor
public class VerifyProxyQryController {
	
	  private final VerifyProxyQryService ibmVerifyService;
	  @PostMapping(value = "/generateotp", produces = "application/json")
		public Mono<GenerateOtpResp> generateOtp (@RequestBody GenerateOtpReq generateOtpReq) {
				return ibmVerifyService.generateOtp(generateOtpReq);
	  }
	  
	  @PostMapping(value = "/verifyotp", produces = "application/json")
	    public Mono<VerifyOtpResp> verifyOtp (@RequestBody VerifyOtpReq verifyOtpReq ) {
			    return ibmVerifyService.verifyOtp (verifyOtpReq);
	  }
	  @PostMapping(value = "/corpUserLogin", produces = "application/json")
	    public Mono<CorpLoginResp> corpLogin(@RequestBody CorpLoginReq corpLoginReq ) {
			    return ibmVerifyService.corpLogin(corpLoginReq);
	  }

	@PostMapping(value = "/get-security-questions", produces = "application/json")
	public Mono<SecurityQuestions> getSecurityQuestionByCif(@RequestBody SecurityQuestionReq securityQuestionReq) {
		return ibmVerifyService.getQuestionByCif(securityQuestionReq);
	}

	@PostMapping(value = "/get-all-knowledge-questions", produces = "application/json")
	public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions(){
		  return ibmVerifyService.getAllKnowledgeQuestions();
	}

	@PostMapping(value = "/validate-knowledge-questions", produces = "application/json")
	public Mono<ValidateKnowledgeQuestionsResp> validateUserKnowledgeQuestions(@RequestBody ValidateKnowledgeQuestionsReq validateKnowledgeQuestionsReq){
		  return ibmVerifyService.validateUserKnowledgeQuestions(validateKnowledgeQuestionsReq);
	}
	@PostMapping(value = "/verifyoldloginpin", produces = "application/json")
	public Mono<GenericResponse> verifyOLdLoginMPin(@RequestBody VerifyOldLoginMPin verifyOldLoginMPin){
		return ibmVerifyService.verifyOLdLoginMPin(verifyOldLoginMPin);
	}

	@PostMapping(value = "/checkMbRegistration", produces = "application/json")
	public Mono<CheckMbRegistrationResp> checkMbRegistration(@RequestBody CheckMbRegistrationReq checkMbRegistrationReq){
		  return ibmVerifyService.checkMbRegistration(checkMbRegistrationReq);
	}

	@PostMapping(value = "/verifytpin", produces = "application/json")
	public Mono<GenericResponse> verifyTpin(@RequestBody VerifyTpin verifyTpin){
		return ibmVerifyService.verifyTpin(verifyTpin);
	}

}
