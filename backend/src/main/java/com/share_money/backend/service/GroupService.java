package com.share_money.backend.service;

import com.share_money.backend.dto.group.CreateGroupRequestDTO;
import com.share_money.backend.dto.group.GetGroupResponse;
import com.share_money.backend.model.Group;
import com.share_money.backend.model.GroupMember;
import com.share_money.backend.model.MemberRole;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.GroupMemberRepo;
import com.share_money.backend.repo.GroupRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupMemberService groupMemberService;

    private final GroupRepo groupRepo;
    private final GroupMemberRepo groupMemberRepo;

    @Transactional
    public void createNewGroup(CreateGroupRequestDTO createGroupRequestDTO, User user) {

        Group group = Group.builder()
                .groupName(createGroupRequestDTO.groupName())
                .groupPurpose(createGroupRequestDTO.groupPurpose())
                .numberOfMembers(createGroupRequestDTO.listOfAddingMember().size() + 1)
                .build();
        Group newGroup = groupRepo.save(group);
        groupMemberService.addGroupMember(newGroup,user.getUserId(), MemberRole.ADMIN);
        for (Long memberId : createGroupRequestDTO.listOfAddingMember()) {
            groupMemberService.addGroupMember(newGroup,memberId, MemberRole.MEMBER);
        }


    }

    public List<GetGroupResponse> getGroup(User user) {


        return groupRepo.findGroupsAndGroupMemberByUserId(user.getUserId())
                .stream()
                .map(g ->{
                    List<String> memberName = g.getGroupMembers()
                            .stream().map(gm -> gm.getMember().getUsername()).toList();
                    return new GetGroupResponse(g.getGroupId(),g.getGroupName(),memberName);

                }

                ).toList();

    }
}
