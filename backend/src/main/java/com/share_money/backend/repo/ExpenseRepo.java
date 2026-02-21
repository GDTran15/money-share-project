package com.share_money.backend.repo;

import com.share_money.backend.dto.expense.GetExpenseForOwnerDTO;
import com.share_money.backend.model.Expense;
import com.share_money.backend.model.ExpenseShareTo;
import com.share_money.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

//    @Query("""
//    select new com.share_money.backend.dto.expense.GetExpenseForOwnerDTO(
//        e.expenseName,
//        e.date,
//        e.expenseType,
//        e.expenseStatus
//    ) from Expense e
//""")
//    List<GetExpenseForOwnerDTO> getOweExpense(@Param("userId") Long userId,
//                                                              @Param("shareToFriend") ExpenseShareTo shareToFriend,
//                                                              @Param("shareToGroup") ExpenseShareTo shareToGroup);

}
