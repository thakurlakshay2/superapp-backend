package com.example.superApp.userService.util;

import com.example.superApp.userService.dto.UserDto;
import com.example.superApp.userService.dto.UserResponseDTO;
import com.example.superApp.userService.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
//        dto.setId(user.getId());
//        dto.setUsername(user.getUsername());
//        dto.setEmail(user.getEmail());
//        dto.setCreatedAt(user.getCreatedAt());
//        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}