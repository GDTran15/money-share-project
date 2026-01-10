package com.share_money.backend.repo;

import com.share_money.backend.model.Friendship;
import com.share_money.backend.model.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepo extends JpaRepository<Friendship, FriendshipId> {

    @Query("""
select count(f) > 0 from Friendship f where 
(f.receiver.userId = :firstUserId and f.requester.userId = :secondUserId) 
or 
(f.requester.userId = :firstUserId and f.receiver.userId = :secondUserId)
""")
    boolean existsFriendshipByUserId( @Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);
}
