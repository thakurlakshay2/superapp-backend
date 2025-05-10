package com.example.superApp.payUrFren.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTOResponse {
        private String username;
        private String email;
        private String phone;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
