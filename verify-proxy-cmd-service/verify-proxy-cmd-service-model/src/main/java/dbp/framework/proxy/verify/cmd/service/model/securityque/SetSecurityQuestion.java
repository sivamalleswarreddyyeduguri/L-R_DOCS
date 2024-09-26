package dbp.framework.proxy.verify.cmd.service.model.securityque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetSecurityQuestion {
    private String cif;
    private String deviceid;
    private String simdata;
    private List<Questions> questions;

}
