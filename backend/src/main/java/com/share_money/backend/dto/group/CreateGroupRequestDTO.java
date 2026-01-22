package com.share_money.backend.dto.group;

import com.share_money.backend.model.GroupPurpose;

import java.util.List;

public record CreateGroupRequestDTO(
        String groupName,
        List<Long> listOfAddingMember,
        GroupPurpose groupPurpose

) {
}
