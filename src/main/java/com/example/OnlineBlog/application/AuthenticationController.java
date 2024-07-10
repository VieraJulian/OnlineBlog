package com.example.OnlineBlog.application;

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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid SignUpRequestDTO authRegisterRequest) {
        return new ResponseEntity<>(userDetailsService.registerUser(authRegisterRequest), HttpStatus.OK);
    }

}
