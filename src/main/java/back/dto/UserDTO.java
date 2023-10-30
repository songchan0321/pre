package back.dto;

import back.entitiy.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter @Setter
@ToString
public class UserDTO {
    private Long id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String birthdate;
    private String gender;
    private int roleId;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .phone(phone)
                .birthdate(birthdate)
                .build();
    }
}