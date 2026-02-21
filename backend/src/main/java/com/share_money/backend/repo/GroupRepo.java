package com.share_money.backend.repo;

import com.share_money.backend.dto.group.GetGroupResponseWithSearch;
import com.share_money.backend.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {

    @Query("""
       select g from Group g join g.groupMembers gmS
       join fetch g.groupMembers gm
       join fetch gm.member 
       where gmS.member.userId = :userId
""")
    List<Group> findGroupsAndGroupMemberByUserId(@Param("userId") Long userId);


}