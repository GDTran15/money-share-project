package com.share_money.backend.dto.expense;

import com.share_money.backend.model.ExpenseShareTo;
import com.share_money.backend.model.ExpenseType;

import java.time.LocalDate;

public record   AddExpenseDTO(
    String expenseName,
    double amount,
    LocalDate date,
    ExpenseType expenseType,
    ExpenseShareTo expenseShareTo,
    Long targetId

) {
}
