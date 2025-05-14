package com.example.superApp.payUrFren.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExpenseShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double share;

    @ManyToOne
    private User user;

    @ManyToOne
    private Expense expense;
}