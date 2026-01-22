package com.share_money.backend.repo;

import com.share_money.backend.dto.friend.GetFriendsResponseDTO;
import com.share_money.backend.model.Friendship;
import com.share_money.backend.model.FriendshipId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface FriendRepo extends JpaRepository<Friendship, FriendshipId> {

    @Query("""
select count(f) > 0 from Friendship f where 
(f.receiver.userId = :firstUserId and f.requester.userId = :secondUserId) 
or 
(f.requester.userId = :firstUserId and f.receiver.userId = :secondUserId)
""")
    boolean existsFriendshipByUserId( @Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);

    @Query("""
    select f from Friendship f where (f.requester.userId = :userId) or (f.receiver.userId = :userId)
""")
    List<Friendship> getFriendshipByUserId(@Param("userId") Long userId);

    @Query("""
    select new com.share_money.backend.dto.friend.GetFriendsResponseDTO(
    case when f.requester.userId = :userId 
    then f.receiver.username 
    else f.requester.username 
    end,
    cast(
    case when f.requester.userId = :userId 
    then f.receiver.userId
    else f.requester.userId
     end as long) )from Friendship f where (f.requester.userId = :userId or f.receiver.userId = :userId) 
     and (
                lower(f.requester.username) like lower(concat('%', :search, '%'))
             or lower(f.receiver.username) like lower(concat('%', :search, '%'))
           )
""")
    Page<GetFriendsResponseDTO> getFriends(Pageable pageable, @Param("search") String search, @Param("userId") Long userId);
}
