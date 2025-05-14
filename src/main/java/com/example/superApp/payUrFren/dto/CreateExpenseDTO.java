package com.example.superApp.payUrFren.dto;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class CreateExpenseDTO {
    private UUID groupId;
    private UUID paidBy;
    private String description;
    private double amount;
    private Map<UUID, Double> splitMap; // userId -> share
}