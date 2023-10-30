package back.service;


import back.dto.UserDTO;

public interface UserService {
    void register(UserDTO userDTO);
    void duplicatedUsername(UserDTO userDTO);
    JwtTokenRespDto login(LoginReqDto loginReqDto);
}