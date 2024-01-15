package bros.flatcode.dto;

import lombok.Data;

public class MemberDto {

    @Data
    public static class Join{

        private String username;

        private String email;

        private String password;

        private Integer age;

        private String address;
    }
}
