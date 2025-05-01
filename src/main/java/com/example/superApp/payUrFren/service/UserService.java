package com.example.superApp.payUrFren.service;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    BaseUserDTO createUser(CreateUserDTO userDTO);
    Optional<BaseUserDTO> getPayUrFrenUser(UUID id);

    Optional<BaseUserDTO> updateUser(UUID userId, BaseUserDTO userDTO);
}
