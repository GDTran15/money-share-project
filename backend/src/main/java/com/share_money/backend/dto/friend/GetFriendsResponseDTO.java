package com.share_money.backend.dto.friend;

import com.share_money.backend.model.FriendStatus;
import com.share_money.backend.model.Friendship;

import java.util.List;

public record GetFriendsResponseDTO(
        String username,
        Long userId

) {
}
