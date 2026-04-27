package com.backend.auth_service.service;

import com.backend.auth_service.config.JwtUtil;
import com.backend.auth_service.entity.Role;
import com.backend.auth_service.entity.User;
import com.backend.auth_service.repository.RoleRepository;
import com.backend.auth_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder; // Thêm cái này
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;// Inject PasswordEncoder

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
//    public void registerUser(String username, String email, String rawPassword) {
//        // 1. Kiểm tra username đã tồn tại chưa
//        if (userRepository.existsByUsername(username)) {
//            throw new RuntimeException("Username đã tồn tại!");
//        }
//
//        // 2. Hash mật khẩu trước khi lưu vào DB
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        // 3. Tạo User mới
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(encodedPassword); // Lưu chuỗi đã Hash
//        user.setEnabled(true);
//
//        // 4. Gán role mặc định (ROLE_USER)
//        Role userRole = roleRepository.findByName("ROLE_USER")
//                .orElseThrow(() -> new RuntimeException("Role không tìm thấy"));
//        user.setRoles(Set.of(userRole));
//
//        // 5. Lưu vào Database
//        userRepository.save(user);
//    }
}