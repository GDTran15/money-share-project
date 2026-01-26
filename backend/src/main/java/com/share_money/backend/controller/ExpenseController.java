package com.share_money.backend.controller;

import com.share_money.backend.dto.expense.AddExpenseDTO;
import com.share_money.backend.model.User;
import com.share_money.backend.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping()
    public ResponseEntity<String> createNewExpense(@RequestBody AddExpenseDTO addExpenseDTO, @AuthenticationPrincipal User user) {
        expenseService.addExpense(addExpenseDTO,user);

        return ResponseEntity.ok("success");
    }


}
