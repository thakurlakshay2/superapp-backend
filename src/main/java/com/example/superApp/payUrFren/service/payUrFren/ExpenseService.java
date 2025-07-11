package com.example.superApp.payUrFren.service.payUrFren;


import com.example.superApp.payUrFren.dto.CreateExpenseDTO;
import com.example.superApp.payUrFren.entity.*;
import com.example.superApp.payUrFren.exception.NotFoundException;
import com.example.superApp.payUrFren.repository.payUrFren.ExpenseRepository;
import com.example.superApp.payUrFren.repository.payUrFren.ExpenseShareRepository;
import com.example.superApp.payUrFren.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExpenseService {
    private final UserService userService;
    private final GroupService groupService;
    private final ExpenseRepository expenseRepository;
    private final ExpenseShareRepository expenseShareRepository;

    public Expense addExpense(CreateExpenseDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Expense data must not be null");
        if (dto.getGroupId() == null) throw new NotFoundException("Group", null);
        if (dto.getPaidBy() == null) throw new NotFoundException("User (paidBy)", null);
        if (dto.getAmount() == null || dto.getAmount() <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (dto.getSplitMap() == null || dto.getSplitMap().isEmpty()) throw new IllegalArgumentException("Split map must not be empty");

        Group group = groupService.getGroup(dto.getGroupId()).orElseThrow(() -> new NotFoundException("Group", dto.getGroupId()));
        User paidBy = userService.getUser(dto.getPaidBy()).orElseThrow(() -> new NotFoundException("User", dto.getPaidBy()));

        Expense expense = new Expense();
        expense.setAmount(java.math.BigDecimal.valueOf(dto.getAmount()));
        expense.setDescription(dto.getDescription());
        expense.setGroup(group);
        expense.setPaidBy(paidBy);
        expense.setCreatedAt(java.time.LocalDateTime.now());
        expense = expenseRepository.save(expense);

        for (java.util.Map.Entry<java.util.UUID, Double> entry : dto.getSplitMap().entrySet()) {
            User user = userService.getUser(entry.getKey()).orElseThrow(() -> new NotFoundException("User", entry.getKey()));
            ExpenseShare share = new ExpenseShare();
            share.setExpense(expense);
            share.setUser(user);
            share.setShare(entry.getValue());
            expenseShareRepository.save(share);
        }
        return expense;
    }
}
