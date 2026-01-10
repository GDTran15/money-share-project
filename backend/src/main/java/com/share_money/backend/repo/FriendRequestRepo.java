package com.share_money.backend.repo;

import com.share_money.backend.dto.friend.GetReceiverFriendRequestResponse;
import com.share_money.backend.dto.friend.GetSenderFriendRequestResponse;
import com.share_money.backend.model.FriendRequest;
import com.share_money.backend.model.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {


    Optional<FriendRequest> getFriendRequestsById(long id);

    @Query("""
        select new com.share_money.backend.dto.friend.GetSenderFriendRequestResponse (
         fr.id, fr.receiver.username, fr.friendStatus
        ) from FriendRequest fr where (fr.sender.userId = :senderId) and (fr.friendStatus = :status)
""")
    List<GetSenderFriendRequestResponse> getFriendRequestsBySenderId(@Param("senderId") long senderId, @Param("status")FriendStatus status);

    @Query("""
        select new com.share_money.backend.dto.friend.GetReceiverFriendRequestResponse (
         fr.id, fr.sender.username, fr.friendStatus
        ) from FriendRequest fr where (fr.receiver.userId = :receiverId) and (fr.friendStatus = :status)
""")
    List<GetReceiverFriendRequestResponse> getFriendRequestsByReceiverId(@Param("receiverId") Long userId,@Param("status") FriendStatus friendStatus);

    @Query("""
        select count(*) from FriendRequest fr where (fr.receiver.userId = :userId) and
        (fr.friendStatus = :status)
""")
    Integer getNumberOfReceiveFriendRequest(@Param("userId") Long userId,@Param("status") FriendStatus friendStatus);


    @Query("""
  
   select count(fr) > 0 from FriendRequest fr
   where (
       (fr.sender.userId = :senderId and fr.receiver.userId = :receiverId)
       or
       (fr.sender.userId = :receiverId and fr.receiver.userId = :senderId)
   )
   and fr.friendStatus = :status
  
""")
    boolean existsFriendRequest(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId, @Param("status") FriendStatus friendStatus);
}
