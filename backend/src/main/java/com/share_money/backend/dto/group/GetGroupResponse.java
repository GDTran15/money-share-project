package com.share_money.backend.dto.group;

import java.util.List;

public record GetGroupResponse(
        Long groupId,
        String groupName,
        List<String> memberName

) {
}
