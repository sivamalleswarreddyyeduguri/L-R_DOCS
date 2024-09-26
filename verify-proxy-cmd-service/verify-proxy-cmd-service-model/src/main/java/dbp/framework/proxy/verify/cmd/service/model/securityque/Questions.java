package dbp.framework.proxy.verify.cmd.service.model.securityque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Questions {
    private String id;
    private String question;
    private String answer;
}
