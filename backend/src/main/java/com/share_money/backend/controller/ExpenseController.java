package com.share_money.backend.controller;

import com.share_money.backend.dto.expense.AddExpenseDTO;
import com.share_money.backend.dto.expense.GetExpenseForOwnerDTO;
import com.share_money.backend.model.User;
import com.share_money.backend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping()
    public ResponseEntity<String> createNewExpense(@Valid @RequestBody AddExpenseDTO addExpenseDTO, @AuthenticationPrincipal User user) {
        expenseService.addExpense(addExpenseDTO,user);

        return ResponseEntity.ok("success");
    }

//    @GetMapping()
//    public ResponseEntity<List<GetExpenseForOwnerDTO>> getExpenseForOwner(@AuthenticationPrincipal User user) {
//       return new ResponseEntity<>(expenseService.getOwnExpense(user), HttpStatus.OK);
//    }



}
