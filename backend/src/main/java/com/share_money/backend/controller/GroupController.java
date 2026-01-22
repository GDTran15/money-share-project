package com.share_money.backend.controller;


import com.share_money.backend.dto.group.CreateGroupRequestDTO;
import com.share_money.backend.dto.group.GetGroupResponse;
import com.share_money.backend.model.GroupMember;
import com.share_money.backend.model.User;
import com.share_money.backend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public String createGroup(@RequestBody CreateGroupRequestDTO createGroupRequestDTO, @AuthenticationPrincipal User user) {

        groupService.createNewGroup(createGroupRequestDTO,user);
        return "Group created";
    }

    @GetMapping
    public List<GetGroupResponse> getGroups(@AuthenticationPrincipal User user) {
     return groupService.getGroup(user);
    }
}
