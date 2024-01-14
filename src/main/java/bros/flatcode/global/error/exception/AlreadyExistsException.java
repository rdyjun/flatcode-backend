package bros.flatcode.global.error.exception;

import bros.flatcode.global.error.ErrorCode;

public class AlreadyExistsException extends BusinessException{

    public AlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
