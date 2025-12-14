package com.chatapp.backend.service;

import com.chatapp.backend.dto.UserDTO;
import com.chatapp.backend.dto.UserRequestDTO;
import com.chatapp.backend.entity.Users;
import com.chatapp.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserRequestDTO userRequestDTO) {
        Users users =
                Users.builder()
                        .name(userRequestDTO.getName())
                        .email(userRequestDTO.getEmail())
                        .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                        .roles(userRequestDTO.getRoles())
                        .build();
        Users savedUser = userRepository.save(users);
        return mapToDTO(savedUser);
    }

    private UserDTO mapToDTO(Users user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Users user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException("User not found with email: " + email));
        return mapToDTO(user);
    }
}
