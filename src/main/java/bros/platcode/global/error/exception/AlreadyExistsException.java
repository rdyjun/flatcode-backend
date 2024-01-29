package bros.platcode.global.error.exception;

import bros.platcode.global.error.ErrorCode;

public class AlreadyExistsException extends BusinessException{

    public AlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
