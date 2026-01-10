package com.share_money.backend.service;

import com.share_money.backend.dto.friend.GetReceiverFriendRequestResponse;
import com.share_money.backend.dto.friend.GetSenderFriendRequestResponse;
import com.share_money.backend.model.FriendRequest;
import com.share_money.backend.model.FriendStatus;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.FriendRepo;
import com.share_money.backend.repo.FriendRequestRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendRequestService {
    
    private final FriendRequestRepo friendRequestRepo;
    private final FriendRepo friendRepo;
    private final FriendService friendService;
    private final UserRepo userRepo;

    public void createFriendRequest(User user, String username) {
        User receiverUser = userRepo.findByUsername(username);
        boolean alreadyFriendRequest = friendRequestRepo.existsFriendRequest(user.getUserId(),receiverUser.getUserId(),FriendStatus.PENDING);
        if (alreadyFriendRequest) {
            throw new RuntimeException("Friend request already exists");
        }
        boolean alreadyFriend = friendRepo.existsFriendshipByUserId(receiverUser.getUserId(),user.getUserId());
        if (alreadyFriend) {
            throw new RuntimeException("Friendship already exists");
        }
        FriendRequest friendRequest = FriendRequest.builder()
                .sender(user)
                .receiver(receiverUser)
                .friendStatus(FriendStatus.PENDING)
                .requestDate(LocalDate.now())
                .build();
        friendRequestRepo.save(friendRequest);
    }

    public List<GetSenderFriendRequestResponse> getAllFriendRequestOfUser( Long userId) {
        return friendRequestRepo.getFriendRequestsBySenderId(userId, FriendStatus.PENDING);
    }

    public List<GetReceiverFriendRequestResponse> getAllFriendRequestSentToUser(Long userId) {
        return friendRequestRepo.getFriendRequestsByReceiverId(userId, FriendStatus.PENDING);
    }

    public Integer getNumberOfIncomingFriendRequest(Long userId) {
        return friendRequestRepo.getNumberOfReceiveFriendRequest(userId,FriendStatus.PENDING);
    }


    public void updateFriendRequest(long friendRequestId, FriendStatus friendStatus) {
        FriendRequest friendRequest = friendRequestRepo.getFriendRequestsById(friendRequestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));
        if (friendStatus.equals((FriendStatus.ACCEPTED))){
            friendService.createFriendShip(friendRequest.getReceiver(), friendRequest.getSender());
            friendRequest.setFriendStatus(friendStatus);
        } else {
            friendRequest.setFriendStatus(friendStatus);
        }
        friendRequestRepo.save(friendRequest);
    }
}
