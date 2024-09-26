package dbp.framework.proxy.verify.qry.service.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubi.dbp.proxy.service.model.GenericException;
import dbp.framework.support.exception.ErrorMessage;
import dbp.framework.support.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpHostConnectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class VerifyProxyQryExceptionHandler {


    @ExceptionHandler(VerifyException.class)
    public ResponseEntity<GenericException> handleClientException(VerifyException exception){
        GenericException errorResponse = new GenericException();
        errorResponse.setError_code(exception.getCode());
        errorResponse.setErrordesc(exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception){
        log.error("Exception Caught in handleRuntimeException : {}",exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
    @ExceptionHandler(HttpHostConnectException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleHttpHostConnectException(Exception ex) {
        log.error("Unhandled exception occurred", ex);
        return buildErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        log.error("Unhandled exception occurred", ex);
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Mono<ResponseEntity<ErrorResponse>> buildErrorResponse(Exception ex, HttpStatus status) {
        String uid = UUID.randomUUID().toString();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse error = objectMapper.readValue(ex.getMessage(), ErrorResponse.class);
            return Mono.just(new ResponseEntity<>(error, status));
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder().errorId(uid)
                    .errors(List.of(new ErrorMessage("PROPER_MESSAGE_NOT_RECIEVED_FROM_SERVICE", ex.getMessage(),null))).build();
            return Mono.just(new ResponseEntity<>(errorResponse, status));
        }
    }
}

