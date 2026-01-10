package com.share_money.backend.controller;

import com.share_money.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;


//
//      @GetMapping("/friends")
//    public ResponseEntity<GetFriendsResponseDTO> getFriends() {
//
//    }

//    @PostMapping("")
//    public ResponseEntity<String> addFriend(@RequestBody AddFriendRequestDTO addFriendRequestDTO, @AuthenticationPrincipal User user) {
//
//        friendService.createFriendShip(addFriendRequestDTO, user.getUserId());
//        return ResponseEntity.ok("Friendship added");
//    }
}
