package com.share_money.backend.service;

import com.share_money.backend.model.Friendship;
import com.share_money.backend.model.FriendshipId;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.FriendRepo;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
