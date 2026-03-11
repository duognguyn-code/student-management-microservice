package com.backend.auth_service.authController;


import com.backend.auth_service.dto.LoginRequestDTO;
import com.backend.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){

        String token = authService.login(request.getUsername(), request.getPassword());

        // Đóng gói String vào ResponseEntity
        return ResponseEntity.ok(token);    }
}
