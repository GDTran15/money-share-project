package com.share_money.backend.repo;

import com.share_money.backend.dto.share_request.GetOweShareRequest;
import com.share_money.backend.model.ShareRequest;
import com.share_money.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRequestRepo extends JpaRepository<ShareRequest, Long> {
    @Query("""
    select new com.share_money.backend.dto.share_request.GetOweShareRequest(
    e.expenseName,
    e.expenseType,
    e.date,
    sr.shareReceiver.username,
    sr.amountToPay,
    sr.shareRequestAnswer
    ) from ShareRequest sr join sr.expense e where sr.shareOwner.userId = :userId
""")
    List<GetOweShareRequest> getOweShareRequest(@Param("userId") Long userId);
}
