package com.share_money.backend.controller;

import com.share_money.backend.dto.friend.GetReceiverFriendRequestResponse;
import com.share_money.backend.dto.friend.GetSenderFriendRequestResponse;
import com.share_money.backend.model.FriendStatus;
import com.share_money.backend.model.User;
import com.share_money.backend.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend-request")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    @PostMapping()
    public ResponseEntity<String> addFriendRequest(@RequestParam String username, @AuthenticationPrincipal User user) {
        friendRequestService.createFriendRequest(user, username);
        return ResponseEntity.ok("success");
    }

  @GetMapping("/sent")
    public ResponseEntity<List<GetSenderFriendRequestResponse>> getFriendRequest(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(friendRequestService.getAllFriendRequestOfUser(user.getUserId()));

  }

  @GetMapping("/receiver")
    public ResponseEntity<List<GetReceiverFriendRequestResponse>> getReceiverFriendRequest(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(friendRequestService.getAllFriendRequestSentToUser(user.getUserId()));
  }

  @GetMapping("/receiver/count")
    public ResponseEntity<Integer> getReceiverFriendRequestCount(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(friendRequestService.getNumberOfIncomingFriendRequest(user.getUserId()));
  }

        @PutMapping("/{friendRequestId}")
    public ResponseEntity<String> friendRequestResponse(@PathVariable long friendRequestId, @RequestParam FriendStatus friendStatus) {
        friendRequestService.updateFriendRequest(friendRequestId, friendStatus);
        return ResponseEntity.ok("success");
    }
}





