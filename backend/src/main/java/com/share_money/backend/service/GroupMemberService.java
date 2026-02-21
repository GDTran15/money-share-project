package com.share_money.backend.service;

import com.share_money.backend.dto.group.GetGroupResponseWithSearch;
import com.share_money.backend.model.Group;
import com.share_money.backend.model.GroupMember;
import com.share_money.backend.model.GroupMemberId;
import com.share_money.backend.model.MemberRole;
import com.share_money.backend.repo.GroupMemberRepo;
import com.share_money.backend.repo.GroupRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupMemberRepo groupMemberRepo;
    private final UserRepo userRepo;


    @Transactional
    public void addGroupMember(Group group, Long memberId , MemberRole memberRole) {
            GroupMemberId groupMemberId = new GroupMemberId(group.getGroupId(), memberId);


             GroupMember groupMember = GroupMember.builder()
                     .id(groupMemberId)
                    .group(group)
                    .member(userRepo.findUserByUserId(memberId))
                    .role(memberRole)
                    .build();
            groupMemberRepo.save(groupMember);

    }

    public Page<GetGroupResponseWithSearch> getGroupsWithSearch(Pageable pageable, String search, Long userId) {
        return groupMemberRepo.getGroupsByUserIdAndSearch(pageable,search ,userId);
    }
}
