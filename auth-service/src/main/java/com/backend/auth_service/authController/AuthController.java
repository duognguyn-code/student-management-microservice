package com.backend.auth_service.authController;


import com.backend.auth_service.dto.LoginRequestDTO;
import com.backend.auth_service.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        log.info("User {} is attempting to login", request.getUsername());
        String token = authService.login(request.getUsername(), request.getPassword());
        log.info("Login successful for user: {}. Token: {}", request.getUsername(), token);
        // Đóng gói String vào ResponseEntity
        return ResponseEntity.ok(token);    }


}
