package bros.flatcode.global.token.constant;

public enum GrantType {

    BEARER("Bearer");

    GrantType(String type) {

        this.type = type;
    }

    private String type;
}
