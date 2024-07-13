package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.application.UserDetailsServiceImpl;
import com.example.OnlineBlog.infrastructure.dto.AuthLoginRequestDTO;
import com.example.OnlineBlog.infrastructure.dto.AuthResponseDTO;
import com.example.OnlineBlog.infrastructure.dto.SignUpRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        try {
            return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid SignUpRequestDTO authRegisterRequest) {
        try {
            return new ResponseEntity<>(userDetailsService.registerUser(authRegisterRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
