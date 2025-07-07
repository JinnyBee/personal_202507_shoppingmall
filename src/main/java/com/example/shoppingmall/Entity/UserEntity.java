package com.example.shoppingmall.Entity;

import com.example.shoppingmall.Constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(
        name = "user_SEQ",
        sequenceName = "user_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "user_SEQ")
    private Integer id;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", length = 255, nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
