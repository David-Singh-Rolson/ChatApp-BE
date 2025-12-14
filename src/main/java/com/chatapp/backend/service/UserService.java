package com.chatapp.backend.service;

import com.chatapp.backend.dto.UserDTO;
import com.chatapp.backend.dto.UserRequestDTO;

public interface UserService {
    UserDTO registerUser(UserRequestDTO userRequestDTO);

    UserDTO getUserByEmail(String email);
}
