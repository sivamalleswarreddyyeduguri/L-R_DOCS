package dbp.framework.proxy.verify.cmd.service.model.corpregistration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorpUserRegistrationReq {
    private String username;
    private String corpname;
    private String mail;
    private String password;
}
