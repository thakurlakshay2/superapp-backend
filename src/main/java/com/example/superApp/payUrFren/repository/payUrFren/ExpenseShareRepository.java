package com.example.superApp.payUrFren.repository.payUrFren;

import com.example.superApp.payUrFren.entity.Expense;
import com.example.superApp.payUrFren.entity.ExpenseShare;
import com.example.superApp.payUrFren.entity.Group;
import com.example.superApp.payUrFren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, Long> {
    List<ExpenseShare> findByExpense(Expense expense);
    List<ExpenseShare> findByUser(User user);
    List<ExpenseShare> findByUserAndSettledFalse(User user);
    List<ExpenseShare> findByGroupAndUserAndSettledFalse(Group group, User user);
}
