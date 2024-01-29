package bros.platcode.global.token.constant;

public enum TokenType {

    ACCESS, REFRESH;

    public static boolean isAccessToken(String type){
        return TokenType.ACCESS.name().equals(type);
    }
}
