package com.share_money.backend.dto.share_request;

import com.share_money.backend.model.ExpenseType;
import com.share_money.backend.model.ShareRequestAnswer;

import java.time.LocalDate;

public record GetOweShareRequest(
        String expenseName,
        ExpenseType expenseType,
        LocalDate date,
        String targetName,
        double amountToPay,
        ShareRequestAnswer shareRequestAnswer
) {
}
