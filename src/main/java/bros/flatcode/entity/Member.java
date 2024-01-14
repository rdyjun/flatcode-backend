package bros.flatcode.entity;

import bros.flatcode.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @Builder
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
}
