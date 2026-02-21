package com.share_money.backend.service;

import com.share_money.backend.dto.expense.AddExpenseDTO;
import com.share_money.backend.dto.expense.GetExpenseForOwnerDTO;
import com.share_money.backend.model.Expense;
import com.share_money.backend.model.ExpenseShareTo;
import com.share_money.backend.model.ExpenseStatus;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .expenseStatus(ExpenseStatus.IN_PROGRESS)
                .build();

        Expense newExpense = expenseRepo.save(expense);

         shareRequestService.createShareRequest(newExpense,addExpenseDTO.expenseShareTo(),addExpenseDTO.targetId(),user,addExpenseDTO.amount());
    }


//    public List<GetExpenseForOwnerDTO> getOwnExpense(User user) {
//        return expenseRepo.getOweExpense(user.getUserId(), ExpenseShareTo.FRIEND,ExpenseShareTo.GROUP);
//    }
}
