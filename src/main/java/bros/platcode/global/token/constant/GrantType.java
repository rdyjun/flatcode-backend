package bros.platcode.global.token.constant;

public enum GrantType {

    BEARER("Bearer");

    GrantType(String type) {

        this.type = type;
    }

    private String type;
}
