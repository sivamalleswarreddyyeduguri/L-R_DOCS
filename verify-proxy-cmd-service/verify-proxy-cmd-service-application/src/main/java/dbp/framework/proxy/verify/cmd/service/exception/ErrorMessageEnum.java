package dbp.framework.proxy.verify.cmd.service.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {

	GENERIC_INPUT_ERR("404","Please provide valid input"),
	GENERIC_BUSINESS_ERR("ERR_GB_00101","Unable to process your request. Please try after some time"),
	GENERIC_TECHNICAL_ERR("500","Technical error occurred while processing your application. Kindly try after some time"),
	;
	@Getter
    private final String code;
	private final String message;

	ErrorMessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

    @Override
	public String toString() {
		return code + ": " + message;
	}
 
}
 