package dbp.framework.proxy.verify.cmd.service.model.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationReq {
    private String cif;
    private String deviceid;
    private String mobile;
    private String simdata;
    private String mail;
}
