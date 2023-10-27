package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.LoginDto;
import com.gdscGCC.ghostform.Dto.UserRequestDto;
import com.gdscGCC.ghostform.Dto.UserResponseDto;
import com.gdscGCC.ghostform.Entity.User;
import com.gdscGCC.ghostform.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserAPIController {
    private final UserService userService;

    /** 회원가입 API */
    @PostMapping("/auth")
    public ResponseEntity<UserResponseDto> auth(@RequestBody UserRequestDto userRequestDto) throws Exception {
        UserResponseDto response = userService.join(userRequestDto);
        if (response == null) {
            System.out.println("Auth Error");
            log.info("Auth Error");
            throw new Exception("Error");
        } else {
            System.out.println("Auth Complete");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    /** 유저 정보 API */
    @PostMapping("/update")
    public ResponseEntity<UserRequestDto> update(@RequestBody UserRequestDto userRequestDto) {
        userService.update(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userRequestDto);
    }

    /** 유저 삭제 API */
    @PostMapping("/remove")
    public ResponseEntity<UserRequestDto> remove(@RequestBody UserRequestDto userRequestDto) {
        userService.update(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userRequestDto);
    }

    /** */
    @PostMapping("/doLogin")
    public void login(@RequestBody LoginDto loginDto) {
        if (userService.login(loginDto)) System.out.println("Login Success");
        else System.out.println("Login Failed");
    }
}