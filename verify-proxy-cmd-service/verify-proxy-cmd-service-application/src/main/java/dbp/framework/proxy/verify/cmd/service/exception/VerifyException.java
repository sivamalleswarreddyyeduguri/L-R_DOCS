package dbp.framework.proxy.verify.cmd.service.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyException extends RuntimeException{

    private String message;
    private Integer statusCode;
    private String code;

    public VerifyException(String message, String code, Integer statusCode){
       super(message);
       this.message=message;
       this.code=code;
       this.statusCode=statusCode;
    }

    @Override
    public String getMessage(){return message;}

}

