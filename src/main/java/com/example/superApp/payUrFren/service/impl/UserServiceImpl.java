package com.example.superApp.payUrFren.service.impl;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;
import com.example.superApp.payUrFren.entity.User;
import com.example.superApp.payUrFren.mapper.UserMapper;
import com.example.superApp.payUrFren.repository.BaseUserRepository;
import com.example.superApp.payUrFren.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
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
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public BaseUserDTO createUser(CreateUserDTO createUserDTO) {
        // Create base user
        System.out.println(" i m here");
        User user = userMapper.toBaseUser(createUserDTO);

        // Set password (encoded) and timestamps
        user.setPassword(createUserDTO.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Save base user
        User savedUser = baseUserRepository.save(user);


        // Return DTO without sensitive information
        return userMapper.toBaseUserDTO(savedUser);
    }

    /**
     * Get PayUrFren user details by ID
     */
    public Optional<BaseUserDTO> getPayUrFrenUser(UUID userId) {
        return baseUserRepository.findById(userId)
                .map(userMapper::toBaseUserDTO);
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
}