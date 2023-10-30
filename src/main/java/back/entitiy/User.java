package back.entitiy;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "member_mst")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String birthdate;

    private String gender;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<Identification> authorities;

    public PrincipalsUserDetails toPrincipal() {

        List<String> roles = authorities.stream()
                .map(Identification::getRole)
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        return PrincipalsUserDetails.builder()
                .userId(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }
}