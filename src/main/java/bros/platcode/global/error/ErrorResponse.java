package bros.platcode.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter @Builder
public class ErrorResponse {

    private int statusCode;
    private String errorCode;
    private String errorMessage;

    public static ErrorResponse of(int statusCode, String errorCode, String errorMessage){

        return ErrorResponse.builder()
                .statusCode(statusCode)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    public static ErrorResponse of(int statusCode, String errorCode, BindingResult bindingResult){

        return ErrorResponse.builder()
                .statusCode(statusCode)
                .errorCode(errorCode)
                .errorMessage(createErrorMessage(bindingResult))
                .build();
    }

    private static String createErrorMessage(BindingResult bindingResult) {

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError error : fieldErrors){

            if (!isFirst){
                sb.append(", ");
            } else isFirst = false;

            sb.append("[");
            sb.append(error.getField());
            sb.append("]");
            sb.append(error.getDefaultMessage());
        }

        return sb.toString();
    }
}
