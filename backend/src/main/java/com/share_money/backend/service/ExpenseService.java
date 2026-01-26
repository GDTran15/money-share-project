package com.share_money.backend.service;

import com.share_money.backend.dto.expense.AddExpenseDTO;
import com.share_money.backend.model.Expense;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.ExpenseRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final ShareRequestService shareRequestService;

    public void addExpense(AddExpenseDTO addExpenseDTO, User user) {
        Expense expense = Expense.builder()
                .expenseName(addExpenseDTO.expenseName())
                .amount(addExpenseDTO.amount())
                .owner(user)
                .expenseShareTo(addExpenseDTO.expenseShareTo())
                .date(addExpenseDTO.date())
                .expenseType(addExpenseDTO.expenseType())
                .build();

        Expense newExpense = expenseRepo.save(expense);

         shareRequestService.createShareRequest(newExpense,addExpenseDTO.expenseShareTo(),addExpenseDTO.targetId(),user,addExpenseDTO.amount());
    }
}
