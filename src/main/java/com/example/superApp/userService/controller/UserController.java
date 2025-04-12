package com.example.superApp.userService.controller;

import com.example.superApp.userService.dto.UserDto;
import com.example.superApp.userService.dto.UserResponseDTO;

import com.example.superApp.userService.entity.User;
import com.example.superApp.userService.service.UserService;

import com.example.superApp.userService.util.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserService service;
    private final UserMapper mapper;


    public UserController(UserService service, UserMapper userMapper) {
        this.service = service;
        this.mapper=userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDto userDTO) {
        User user = mapper.toEntity(userDTO);
        User createdUser = service.createUser(user);
        return ResponseEntity.ok(mapper.toResponseDTO(createdUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = service.getAllUsers()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
//        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
