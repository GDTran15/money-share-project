package com.share_money.backend.dto.expense;

import com.share_money.backend.model.ExpenseShareTo;
import com.share_money.backend.model.ExpenseStatus;
import com.share_money.backend.model.ExpenseType;

import java.time.LocalDate;

public record GetExpenseForOwnerDTO(
            Long shareRequestId,
            String expenseName,
            LocalDate date,
            ExpenseType expenseType,
            ExpenseStatus expenseStatus

) {
}
