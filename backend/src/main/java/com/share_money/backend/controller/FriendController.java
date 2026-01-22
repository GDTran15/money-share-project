package com.share_money.backend.controller;

import com.share_money.backend.dto.friend.GetFriendsResponseDTO;
import com.share_money.backend.model.User;
import com.share_money.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;



      @GetMapping()
    public ResponseEntity<List<GetFriendsResponseDTO>> getFriends(@AuthenticationPrincipal User user) {

          return ResponseEntity.ok(friendService.getFriendOfUser(user));
      }

      @GetMapping("search")
      public ResponseEntity<PagedModel<GetFriendsResponseDTO>> getFriendsByName(
              @RequestParam(value = "page" , defaultValue = "0") int page,
              @RequestParam(value = "size", defaultValue = "5") int size,
              @RequestParam(value = "search", defaultValue = "") String search,
              @AuthenticationPrincipal User user
      ){
          Pageable pageable = PageRequest.of(page, size);
          Page<GetFriendsResponseDTO> pageModel = friendService.getFriends(pageable ,search,user.getUserId());
        return ResponseEntity.ok(new PagedModel<>(pageModel));
      }


//    @PostMapping("")
//    public ResponseEntity<String> addFriend(@RequestBody AddFriendRequestDTO addFriendRequestDTO, @AuthenticationPrincipal User user) {
//
//        friendService.createFriendShip(addFriendRequestDTO, user.getUserId());
//        return ResponseEntity.ok("Friendship added");
//    }
}
