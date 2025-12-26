package com.chatapp.backend.controller;

import com.chatapp.backend.dto.*;
import com.chatapp.backend.exception.UnauthorizedException;
import com.chatapp.backend.security.JwtUtil;
import com.chatapp.backend.service.UserService;
import com.chatapp.backend.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserRequestDTO dto) {
        UserDTO user= userService.registerUser(dto);
        return ResponseUtil.success(user,"User Registered Successfully", HttpStatus.CREATED);
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JWTResponseDTO>> login(@RequestBody LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());

            String token = jwtUtil.generateToken(userDetails.getUsername());

            JWTResponseDTO response = new JWTResponseDTO();
            response.setToken(token);
            response.setEmail(userDetails.getUsername());
            return ResponseUtil.success(response,"Login Successful",HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid email or password , try again !");
        }
    }
}
