package dbp.framework.proxy.verify.qry.service.exception;

public enum ErrorConst {

	UNABLE_TO_PROCESS("UNABLE_TO_PROCESS", "Unable to Process your request"),
	RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Requested resource not found"),
	NO_RECORD_FOUND("NO_RECORD_FOUND", "No records found for given input"),
	ACCT_NOT_FOUND("ELR_0001", "Please open current account.."),
	ALREADY_REGISTERED("ALREADY_REGISTERED", "Already registered Please login"),
	LOGIN_WITH_CORP_ID("ELR_0002", "Please login using corp id"),
	AADHAR_VALIDATION_FAILED("AADHAR_VALIDATION_FAILED", "Please enter valid Aadhaar number"),
	INVALID_OPERATION_TYPE("INVALID_OPERATION_TYPE","invalid operation"),
	OTP_VALIDATION_FAILED("OTP_VALIDATION_FAILED","Please enter valid Aadhaar otp"),
	DEBIT_VERIFICATION_FAILED("DEBIT_VERIFICATION_FAILED","Debit card verification has failed. Please check your card details and try again."),
	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR","Internal Server Error");
	private final String code;
	private final String message;

	ErrorConst(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + message;
	}

}
