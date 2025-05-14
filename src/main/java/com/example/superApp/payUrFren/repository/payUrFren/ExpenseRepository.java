package com.example.superApp.payUrFren.repository.payUrFren;

import com.example.superApp.payUrFren.entity.Expense;
import com.example.superApp.payUrFren.entity.Group;
import com.example.superApp.payUrFren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByGroup(Group group);
    List<Expense> findByPaidBy(User user);
}

