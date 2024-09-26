package dbp.framework.proxy.verify.qry.service.exception;

import lombok.Getter;

@Getter
public class VerifyException extends RuntimeException {

    private String message;
    private Integer statusCode;
    private String code;

    public VerifyException(String message, String code, Integer statusCode) {
        super(message);
        this.message = message;
        this.code = code;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

