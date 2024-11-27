package com.volunticket.domain.user.controller;

import com.volunticket.domain.user.dto.LoginDto;
import com.volunticket.domain.user.dto.UserDto;
import com.volunticket.domain.user.entity.User;
import com.volunticket.domain.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        User user = userService.registerUser(email, username, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/check")
    public Boolean checkEmail(@RequestParam String email) {
        return userService.checkEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String token = userService.login(loginDto.getEmail(), loginDto.getPassword());

        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);  // HTTPS에서만 전송
        cookie.setPath("/");  // 모든 경로에서 사용 가능
        cookie.setMaxAge(60 * 60 * 24); // 1시간
        response.addCookie(cookie);

        User user = userService.login(loginDto.getEmail());
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        return ResponseEntity.ok(userDto);
    }
}
