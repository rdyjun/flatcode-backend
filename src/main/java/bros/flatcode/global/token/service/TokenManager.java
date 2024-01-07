package bros.flatcode.global.token.service;

import bros.flatcode.entity.Role;
import bros.flatcode.global.error.ErrorCode;
import bros.flatcode.global.error.exception.AuthenticationException;
import bros.flatcode.global.token.constant.TokenType;
import bros.flatcode.global.token.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpireTime;

    private final String refreshTokenExpireTime;

    private final String tokenSecret;

    public TokenDto createJwtTokenDto(Long id, String username, Role role){

        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();
    }

    private Date createAccessTokenExpireTime() {

        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime));
    }

    private Date createRefreshTokenExpireTime() {

        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpireTime));
    }

    public String createAccessToken(Long id, String username, Role role, Date expirationTime){

        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name()) // AccessToken 설정
                .setIssuedAt(new Date(System.currentTimeMillis())) // Token 생성 시간
                .setExpiration(expirationTime) // Token 만료 시간
                .claim("id", id)
                .claim("username", username)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8)) // 암호화 알고리즘 설정
                .setHeaderParam("type", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Long id, Date expirationTime){

        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationTime)
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return refreshToken;
    }

    public void validateToken(String token){

        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e){
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }


    public Claims getTokenClaims(String token){
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        return claims;
    }

    public Long getUserIdFromToken(String token){

        return Long.parseLong(getTokenClaims(token).getId());
    }
}
