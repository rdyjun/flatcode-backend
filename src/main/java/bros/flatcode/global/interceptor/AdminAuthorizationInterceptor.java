package bros.flatcode.global.interceptor;

import bros.flatcode.entity.Role;
import bros.flatcode.global.error.ErrorCode;
import bros.flatcode.global.error.exception.AuthenticationException;
import bros.flatcode.global.token.service.TokenManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // preflight 요청 넘기기
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String role = (String) tokenClaims.get("role");

        if (!role.equals(Role.ADMIN.name())) {
                throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
        }

        return true;
    }
}
