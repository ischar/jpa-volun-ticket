package com.volunticket.domain.user.controller;

import com.volunticket.domain.user.dto.LoginDto;
import com.volunticket.domain.user.entity.User;
import com.volunticket.domain.user.service.UserService;
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
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.ok(token);
    }
}
