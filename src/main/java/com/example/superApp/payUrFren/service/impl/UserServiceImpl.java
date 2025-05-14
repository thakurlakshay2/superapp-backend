package com.example.superApp.payUrFren.service.impl;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;
import com.example.superApp.payUrFren.dto.UserDTOResponse;
import com.example.superApp.payUrFren.entity.User;
import com.example.superApp.payUrFren.mapper.UserMapper;
import com.example.superApp.payUrFren.repository.BaseUserRepository;
import com.example.superApp.payUrFren.service.UserService;
import lombok.AllArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final BaseUserRepository baseUserRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserDTOResponse createUser(CreateUserDTO createUserDTO) {
        // Check for uniqueness
        if (baseUserRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (baseUserRepository.existsByUsername(createUserDTO.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }

        // Map DTO to entity
        User user = userMapper.toBaseUser(createUserDTO);
        // Encode password and set timestamps
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Save user
        User savedUser = baseUserRepository.save(user);

        // Return response DTO
        return userMapper.toUserDTOResponse(savedUser);
    }

    /**
     * Get PayUrFren user details by ID
     */
    public Optional<BaseUserDTO> getPayUrFrenUser(UUID userId) {
        return baseUserRepository.findById(userId)
                .map(userMapper::toBaseUserDTO);
    }

    @Override
    public Optional<BaseUserDTO> loadUserByEmail(String email) {
        return baseUserRepository.findByEmail(email).map(userMapper::toBaseUserDTO);
    }

    /**
     * Update base user information and synchronize with PayUrFren user
     */
    @Transactional
    public Optional<BaseUserDTO> updateUser(UUID userId, BaseUserDTO userDTO) {
        Optional<User> baseUserOpt = baseUserRepository.findById(userId);

        if (baseUserOpt.isPresent()) {
            User user = baseUserOpt.get();

            // Update base user
            userMapper.updateBaseUserFromDTO(userDTO, user);
            user.setUpdatedAt(LocalDateTime.now());
            User updatedUser = baseUserRepository.save(user);

            return Optional.of(userMapper.toBaseUserDTO(updatedUser));
        }

        return Optional.empty();
    }

    @Override
    public  Optional<User> getUser(UUID userId){
        return baseUserRepository.findById(userId);

    }
}