package com.example.superApp.payUrFren.service;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;
import com.example.superApp.payUrFren.dto.UserDTOResponse;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDTOResponse createUser(CreateUserDTO userDTO);
    Optional<BaseUserDTO> getPayUrFrenUser(UUID id);
    Optional<BaseUserDTO> loadUserByEmail(String email);
    Optional<BaseUserDTO> updateUser(UUID userId, BaseUserDTO userDTO);
}
