package dbp.framework.proxy.verify.cmd.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@ConfigurationProperties(prefix = "verify.proxy")
@Component
@Getter
public class VerifyProxyConfiguration {

    private String baseUrl;
    private String registrationEndpoint;
    private String setTpinEndpoint;
    private String setMPinEndpoint;
    private String setPasswordEndpoint;
    private String securityQuestionEndpoint;
    private String corpUserRegistrationEndpoint;
}

