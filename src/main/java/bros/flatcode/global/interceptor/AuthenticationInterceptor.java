package bros.flatcode.global.interceptor;

import bros.flatcode.global.error.ErrorCode;
import bros.flatcode.global.error.exception.AuthenticationException;
import bros.flatcode.global.token.constant.TokenType;
import bros.flatcode.global.token.service.TokenManager;
import bros.flatcode.global.util.AuthorizationUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        // Pass Preflight Request
        if (request.getMethod().equals("OPTIONS"))
            return true;

        // 1. Authorization Header 검증 (Bearer)
        String authorization = request.getHeader("Authorization");
        AuthorizationUtils.validateAuthorization(authorization);

        // 2. AccessToken 검증
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        // 3. Token Type 검증
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String subject = tokenClaims.getSubject();

        if (!TokenType.isAccessToken(subject))
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);

        return true;
    }
}
