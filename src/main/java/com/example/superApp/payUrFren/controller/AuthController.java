package com.example.superApp.payUrFren.controller;

import com.example.superApp.payUrFren.dto.*;
import com.example.superApp.payUrFren.security.JwtService;
import com.example.superApp.payUrFren.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public UserDTOResponse register(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        System.out.println(request.getEmail());
        BaseUserDTO userDTO = userService.loadUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), userDTO.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(userDTO.getEmail());
        return new AuthResponse(token);
    }
}
