package com.backend.auth_service.service;

import com.backend.auth_service.config.JwtUtil;
import com.backend.auth_service.entity.User;
import com.backend.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Thêm cái này
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        // 1. Tìm user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Kiểm tra mật khẩu đã mã hóa
        // password: mật khẩu thô từ client
        // user.getPassword(): mật khẩu đã mã hóa trong DB
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3. Lấy danh sách Role (nếu user có nhiều role) để đưa vào Token
        String roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.joining(","));

        return jwtUtil.generateToken(user.getUsername(), roles);
    }
}