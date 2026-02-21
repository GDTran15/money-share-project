package com.share_money.backend.dto.expense;

import com.share_money.backend.model.ExpenseShareTo;
import com.share_money.backend.model.ExpenseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record   AddExpenseDTO(
    @NotBlank(message = "Please enter expense name")
    String expenseName,
    @NotNull(message = "Please enter the expense amount")
    double amount,
    @NotNull(message = "Please enter the expense date")
    LocalDate date,
    @NotNull(message = "Please choose expense type")
    ExpenseType expenseType,
    @NotNull(message = "Choose Friend or Group")
    ExpenseShareTo expenseShareTo,
    @NotNull(message = "Choose share target")
    Long targetId

) {
}
