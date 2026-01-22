package com.share_money.backend.service;

import com.share_money.backend.dto.friend.GetFriendsResponseDTO;
import com.share_money.backend.model.Friendship;
import com.share_money.backend.model.FriendshipId;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.FriendRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepo friendRepo;
    private final UserRepo userRepo;


    public void createFriendShip(User receiver, User requester) {
        FriendshipId friendshipId = new FriendshipId(requester.getUserId(),receiver.getUserId());
        Friendship friendship = Friendship.builder()
                .friendshipId(friendshipId)
                .requester(requester)
                .receiver(receiver)
                .friendshipDate(LocalDate.now())
                .build();
        friendRepo.save(friendship);

    }

    public List<GetFriendsResponseDTO> getFriendOfUser(User user) {
       List<Friendship> friendShips = friendRepo.getFriendshipByUserId(user.getUserId());

        List<GetFriendsResponseDTO> friendsResponseDTOS = friendShips.stream()
               .map(friendship -> {
                   User friend = friendship.getReceiver().getUserId() == user.getUserId() ? friendship.getRequester() : friendship.getReceiver();
                   return new GetFriendsResponseDTO(
                           friend.getUsername(),
                           friend.getUserId()
                   );

               }).toList();
    return friendsResponseDTOS;
    }

    public Page<GetFriendsResponseDTO> getFriends(Pageable pageable, String search, Long userId) {

        return friendRepo.getFriends(pageable,search ,userId);
    }
}
