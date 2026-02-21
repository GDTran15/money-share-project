package com.share_money.backend.repo;

import com.share_money.backend.dto.group.GetGroupResponseWithSearch;
import com.share_money.backend.model.Group;
import com.share_money.backend.model.GroupMember;
import com.share_money.backend.model.GroupMemberId;
import com.share_money.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupMemberRepo extends JpaRepository<GroupMember, GroupMemberId> {


    List<GroupMember> findByMember(User member);

    @Query("""
    select gm from GroupMember gm where gm.group.groupId = :groupId
""")
    List<GroupMember> findByGroupId(@Param("groupId") Long groupId);


    @Query("""
    select new com.share_money.backend.dto.group.GetGroupResponseWithSearch(
    gm.group.groupName,
    gm.group.groupId
    ) from GroupMember gm
    where (gm.member.userId = :userId) 
    and (lower(gm.group.groupName) like lower(concat('%',:search,'%') ) ) 
""")
    Page<GetGroupResponseWithSearch> getGroupsByUserIdAndSearch(Pageable pageable,
                                                                @Param("search") String search,
                                                                @Param("userId") Long userId);
}
