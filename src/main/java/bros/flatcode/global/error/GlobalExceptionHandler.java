package bros.flatcode.global.error;

import bros.flatcode.global.error.exception.BusinessException;
import bros.flatcode.global.error.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * BusinessException 발생 메시지 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(BusinessException e){
        log.error("BusinessException", e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.NOT_FOUND.value(), e.getErrorCode().getErrorCode(), e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }

    /**
     * 나머지 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("Exception", e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
