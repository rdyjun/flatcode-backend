package bros.flatcode.entity;

public enum Role {

    COMMON("Common"), ADMIN("Admin");

    Role(String role){
        this.role = role;
    }

    private String role;
}
