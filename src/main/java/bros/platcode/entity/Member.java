package bros.platcode.entity;

import bros.platcode.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    private String password;

    private Integer age;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Long updateUsername(String username){

        this.username = username;

        return this.id;
    }
}
