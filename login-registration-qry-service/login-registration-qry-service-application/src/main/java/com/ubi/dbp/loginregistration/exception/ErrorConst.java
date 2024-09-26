package com.ubi.dbp.loginregistration.exception;

public enum ErrorConst {

    UNABLE_TO_PROCESS("UNABLE_TO_PROCESS", "Unable to Process your request"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Requested resource not found"),
    NO_RECORD_FOUND("NO_RECORD_FOUND", "No records found for given input"),
    ACCT_NOT_FOUND("ELR_0001", "Please open current account.."),
    ALREADY_REGISTERED("ALREADY_REGISTERED", "Already registered Please login"),
    LOGIN_WITH_CORP_ID("ELR_0002", "Please login using corp id"),
    AADHAR_VALIDATION_FAILED("AADHAR_VALIDATION_FAILED", "Please enter valid Aadhaar number"),
    INVALID_OPERATION_TYPE("INVALID_OPERATION_TYPE", "invalid operation"),
    OTP_VALIDATION_FAILED("OTP_VALIDATION_FAILED", "Please enter valid Aadhaar otp"),
    DEBIT_VERIFICATION_FAILED("DEBIT_VERIFICATION_FAILED", "Debit card verification has failed. Please check your card details and try again."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal Server Error"),
    INVALID_CARD("14", "Invalid Card"),
    FORMAT_ERROR("30", "Format Error"),
    INSUFFICIENT_FUNDS("51", "Insufficient Funds"),
    EXPIRED_CARD("54", "Expired Card"),
    INCORRECT_PIN("55", "Incorrect PIN"),
    WITHDRAWAL_LIMIT_EXCEEDED("61", "Exceeds Withdrawal Limit"),
    UNAUTHORIZED_USAGE("62", "Unauthorized Usage"),
    PIN_TRIES_EXCEEDED("75", "PIN Tries Exceeded"),
    SYSTEM_ERROR("88", "System Error"),
    RECORD_NOT_FOUND("25", "Failed - Record Not Found"),
    CONVERSION_ERROR("27", "Failed Due to Errors in Conversion"),
    RECORD_ALREADY_EXISTS("N4", "Record Already Exists"),
    RECORD_NOT_EXIST_CHANGE("N5", "Record Does Not Exist"),
    OTP_GENERATION_LIMIT("OTP_GENERATION_LIMIT", "Exceeds maximum OTP generation limit"),
    TXN_MISMATCH("TXN_MISMATCH", "txn mismatch"),
    OTP_ATTEMPT_EXCEEDED("OTP_ATTEMPT_EXCEEDED", "Retry attempt exceeded. Generate new OTP");
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

    public static ErrorConst fromCode(String code) {
        for (ErrorConst error : values()) {
            if (error.getCode().equals(code)) {
                return error;
            }
        }
        throw new LoginRegistrationException(ErrorConst.UNABLE_TO_PROCESS);
    }
}
