package back.service;

import back.dto.UserDTO;
import back.entitiy.Identification;
import back.entitiy.User;
import back.repository.IdentificationRepository;
import back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IdentificationRepository identificationRepository;
    private final AuthenticationManagerBuilder authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void register(UserDto userDto) {

        User userEntity = userDto.toEntity();
        userEntity = userRepository.save(userEntity);

        Role roleMember = roleRepository.findByRoleName("ROLE_USER");

        Identification identification = Identification.builder()
                .memberId(userEntity.getId())
                .roleId((long) roleMember.getId())
                .build();

        identificationRepository.save(identification);
    }

    @Override
    public void register(UserDTO userDTO) {

    }

    @Override
    public void duplicatedUsername(UserDTO userDTO) {
        User userEntity = userRepository.findByUsername(userDTO.getUsername());

        if(userEntity != null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("username", "이미 사용중인 사용자 이름입니다.");

            throw new CustomException("이미 사용중인 사용자 이름입니다.");
        }
    }

    @Override
    public JwtTokenRespDto login(LoginReqDto loginReqDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword());

        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.createToken(authentication);
    }
}