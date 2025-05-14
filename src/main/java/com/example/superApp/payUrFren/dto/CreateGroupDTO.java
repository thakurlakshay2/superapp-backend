package com.example.superApp.payUrFren.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateGroupDTO {
    private String name;
    private List<UUID> memberIds;
}
