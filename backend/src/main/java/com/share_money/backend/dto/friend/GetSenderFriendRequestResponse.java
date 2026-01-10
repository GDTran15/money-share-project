package com.share_money.backend.dto.friend;


import com.share_money.backend.model.FriendStatus;

public record GetSenderFriendRequestResponse(
        Long friendRequestId,
        String requesterName,
        FriendStatus status

) {
}
