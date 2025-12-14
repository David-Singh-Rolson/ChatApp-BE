package com.chatapp.backend.controller;

import com.chatapp.backend.dto.*;
import com.chatapp.backend.security.JwtUtil;
import com.chatapp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // register
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserRequestDTO dto) {
        return userService.registerUser(dto);
    }

    // login
    @PostMapping("/login")
    public JWTResponseDTO login(@RequestBody LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());

        String token = jwtUtil.generateToken(userDetails.getUsername());

        JWTResponseDTO response = new JWTResponseDTO();
        response.setToken(token);
        response.setEmail(userDetails.getUsername());
        return response;
    }
}
