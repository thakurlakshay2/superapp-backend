package com.example.superApp.payUrFren.dto;


import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
}
