package dbp.framework.proxy.verify.cmd.service.model.setpassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetPasswordReq {
    private String cif;
    private String password;
    private String confirmpassword;
}
