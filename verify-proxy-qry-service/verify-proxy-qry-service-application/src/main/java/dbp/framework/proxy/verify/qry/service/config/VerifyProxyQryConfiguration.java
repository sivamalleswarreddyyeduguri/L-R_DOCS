package dbp.framework.proxy.verify.qry.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "verify.proxy.qry")
public class VerifyProxyQryConfiguration {
	private String baseUrl;
	private String generateOtpEndpoint;
	private String verifyOtpEndpoint;
	private String corpUserLoginEndpoint;
	private String getSecurityQuestionByCif;
	private String knowledgeQuestions;
    private String validateKnowledgeQuestions;
	private String verifyOldLoginMpinEndpoint;
	private String verifyTpinEndpoint;
	private String checkMbRegistration;
}
