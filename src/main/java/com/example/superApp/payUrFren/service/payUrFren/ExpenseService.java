package com.example.superApp.payUrFren.service.payUrFren;


import com.example.superApp.payUrFren.dto.CreateExpenseDTO;
import com.example.superApp.payUrFren.entity.*;
import com.example.superApp.payUrFren.mapper.UserMapper;
import com.example.superApp.payUrFren.repository.BaseUserRepository;
import com.example.superApp.payUrFren.repository.payUrFren.ExpenseRepository;
import com.example.superApp.payUrFren.repository.payUrFren.ExpenseShareRepository;
import com.example.superApp.payUrFren.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ExpenseService {
    private final UserService userService;
    private final GroupService groupService;
    private final ExpenseRepository expenseRepository;
    private final ExpenseShareRepository expenseShareRepository;

    public Expense addExpense(CreateExpenseDTO dto) {
        Group group=groupService.getGroup(dto.getGroupId()).orElseThrow();
        User paidBy= userService.getUser(dto.getPaidBy()).orElseThrow();

        Expense expense = new Expense();

        expense.setAmount(BigDecimal.valueOf(dto.getAmount()));
        expense.setDescription(dto.getDescription());
        expense.setGroup(group);
        expense.setPaidBy(paidBy);
        expense.setCreatedAt(LocalDateTime.now());
        expense = expenseRepository.save(expense);

        for (Map.Entry<UUID, Double> entry : dto.getSplitMap().entrySet()) {
            User user = userService.getUser(entry.getKey()).orElseThrow();
            ExpenseShare share = new ExpenseShare();
            share.setExpense(expense);
            share.setUser(user);
            share.setShare(entry.getValue());
            expenseShareRepository.save(share);
        }
        return expense;
    }
}
