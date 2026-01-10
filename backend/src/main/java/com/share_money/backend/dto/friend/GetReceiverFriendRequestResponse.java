package com.share_money.backend.dto.friend;

import com.share_money.backend.model.FriendStatus;

public record GetReceiverFriendRequestResponse (
        Long friendRequestId,
        String receiverName,
        FriendStatus status
){
}
