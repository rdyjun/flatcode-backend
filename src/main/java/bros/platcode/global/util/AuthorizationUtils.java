package bros.platcode.global.util;

import bros.platcode.global.error.ErrorCode;
import bros.platcode.global.error.exception.AuthenticationException;
import bros.platcode.global.token.constant.GrantType;
import org.springframework.util.StringUtils;

public class AuthorizationUtils {

    public static void validateAuthorization(String authorization){

        // 1. Authorization 필수값 체크
        if (!StringUtils.hasText(authorization))
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);

        // 2. Authorization header Bearer 체크
        String[] authorizationHeader = authorization.split(" ");

        if (authorizationHeader.length < 2 || !GrantType.BEARER.equals(authorizationHeader[0]))
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
    }
}
