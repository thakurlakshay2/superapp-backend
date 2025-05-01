package com.example.superApp.payUrFren.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

// Base DTO for user operations
@Data
public class BaseUserDTO {
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

