package com.share_money.backend.repo;

import com.share_money.backend.model.ShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShareRequestRepo extends JpaRepository<ShareRequest, Long> {

}
