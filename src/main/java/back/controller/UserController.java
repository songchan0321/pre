package back.controller;

import back.dto.UserDTO;
import back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<? extends ResponseDto> register(@RequestBody @Valid UserDTO userDTO){
        // 회원가입 로직
        userService.duplicatedUsername(userDTO);
        userService.register(userDTO);
        return ResponseEntity.ok().body(ResponseDto.ofDefault());
    }
    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<? extends ResponseDto> login(@RequestBody @Valid LoginReqDto loginReqDto){
        // 로그인 로직
        return ResponseEntity.ok().body(DataResponseDto.of(userService.login(loginReqDto)));
    }

    // 회원정보 수정

    // 회원정보 삭제

    // 회원정보 조회

    // 회원목록 조회


}