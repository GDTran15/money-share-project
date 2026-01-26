package com.share_money.backend.service;

import com.share_money.backend.model.*;
import com.share_money.backend.repo.GroupMemberRepo;
import com.share_money.backend.repo.ShareRequestRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShareRequestService {

    private final UserRepo userRepo;
    private final GroupMemberRepo groupMemberRepo;
    private final ShareRequestRepo shareRequestRepo;


    @Transactional
    public void createShareRequest(Expense newExpense, ExpenseShareTo expenseShareTo, Long targetId, User user, double amount) {
        if (expenseShareTo == ExpenseShareTo.FRIEND) {
            ShareRequest shareRequest = ShareRequest.builder()
                    .shareRequestAnswer(ShareRequestAnswer.IN_PROGRESS)
                    .shareRequestStatus(ShareRequestStatus.IN_PROGRESS)
                    .shareOwner(user)
                    .shareReceiver(userRepo.findUserByUserId(targetId))
                    .amountToPay(amount/2)
                    .expense(newExpense)
                    .build();
            shareRequestRepo.save(shareRequest);
        } else {
            List<Long> groupMembersIds = groupMemberRepo.findByGroupId(targetId)
                    .stream().map(groupMember -> groupMember.getMember().getUserId())
                    .toList();
            for (Long groupMemberId : groupMembersIds) {
                if (groupMemberId.equals(user.getUserId())) {
                    continue;
                }
                ShareRequest shareRequest = ShareRequest.builder()
                        .shareRequestAnswer(ShareRequestAnswer.IN_PROGRESS)
                        .shareRequestStatus(ShareRequestStatus.IN_PROGRESS)
                        .shareOwner(user)
                        .shareReceiver(userRepo.findUserByUserId(groupMemberId))
                        .amountToPay(amount/groupMembersIds.size())
                        .expense(newExpense)
                        .build();
                shareRequestRepo.save(shareRequest);
            }
        }
    }
}
