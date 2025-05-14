package com.example.superApp.payUrFren.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;
}