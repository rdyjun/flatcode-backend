package bros.flatcode.global.error.exception;

import bros.flatcode.global.error.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
