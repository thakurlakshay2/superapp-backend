package com.example.superApp.payUrFren.controller;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;

import com.example.superApp.payUrFren.dto.UserDTOResponse;
import com.example.superApp.payUrFren.service.UserService;

import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

     @GetMapping
     public ResponseEntity<String> healthCheck(){
         return new ResponseEntity<>("Healthy",HttpStatus.OK);
     }
    @PostMapping("/createUser")
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody CreateUserDTO userDTO) {
        System.out.println("i am called");
        UserDTOResponse user=userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseUserDTO> getUser(@PathVariable UUID id) {
        BaseUserDTO user=userService.getPayUrFrenUser(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
