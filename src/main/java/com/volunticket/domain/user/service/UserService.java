package com.volunticket.domain.user.service;

import com.volunticket.domain.user.entity.User;
import com.volunticket.domain.user.exception.UserAlreadyExistsException;
import com.volunticket.domain.user.exception.UserNotFoundException;
import com.volunticket.domain.user.repository.UserRepository;
import com.volunticket.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String email, String username, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.save(user);
    }

    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다."));
        if (passwordEncoder.matches(password, user.getPassword()) && user.getEmail().equals(email)) {
            String token = jwtUtil.generateToken(email);
            return token;
        }

        return null;
    }

    public User login(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다."));

        return user;
    }

    public Boolean checkToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String extractEmail(String token) {
        return jwtUtil.extractEmail(token);
    }
}
