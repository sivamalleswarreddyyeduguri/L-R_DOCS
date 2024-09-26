package dbp.framework.proxy.verify.cmd.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericException {
    private String error_code;
    private String errordesc;
}

