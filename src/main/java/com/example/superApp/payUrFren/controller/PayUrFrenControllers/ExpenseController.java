package com.example.superApp.payUrFren.controller.PayUrFrenControllers;

import com.example.superApp.payUrFren.dto.CreateExpenseDTO;
import com.example.superApp.payUrFren.entity.Expense;
import com.example.superApp.payUrFren.service.payUrFren.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payUrFren/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.addExpense(dto));
    }
}
