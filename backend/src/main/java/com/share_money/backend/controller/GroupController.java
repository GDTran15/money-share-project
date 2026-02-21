package com.share_money.backend.controller;


import com.share_money.backend.dto.friend.GetFriendsResponseDTO;
import com.share_money.backend.dto.group.CreateGroupRequestDTO;
import com.share_money.backend.dto.group.GetGroupResponse;
import com.share_money.backend.dto.group.GetGroupResponseWithSearch;
import com.share_money.backend.model.GroupMember;
import com.share_money.backend.model.User;
import com.share_money.backend.service.GroupMemberService;
import com.share_money.backend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    @PostMapping
    public String createGroup(@RequestBody CreateGroupRequestDTO createGroupRequestDTO, @AuthenticationPrincipal User user) {

        groupService.createNewGroup(createGroupRequestDTO,user);
        return "Group created";
    }

    @GetMapping("/search")
    public ResponseEntity<PagedModel<GetGroupResponseWithSearch>> getGroupByName(
            @RequestParam(value = "page" , defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "search", defaultValue = "") String search,
            @AuthenticationPrincipal User user
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<GetGroupResponseWithSearch> pageModel = groupMemberService.getGroupsWithSearch(pageable ,search,user.getUserId());
        return ResponseEntity.ok(new PagedModel<>(pageModel));
    }

    @GetMapping
    public List<GetGroupResponse> getGroups(@AuthenticationPrincipal User user) {
     return groupService.getGroup(user);
    }


}
