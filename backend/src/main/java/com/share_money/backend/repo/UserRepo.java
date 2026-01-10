package com.share_money.backend.repo;

import com.share_money.backend.dto.user.GetAllUserByNameResponseDTO;
import com.share_money.backend.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByUserId(Long userId);

    Optional<User> findOptionByUsername(String username);

    boolean existsByUsername (String username);

    boolean existsByEmail(String email);

    User findByUsername( String username);

    @Query("""
        select new com.share_money.backend.dto.user.GetAllUserByNameResponseDTO(
                u.userId,u.username
                ) from User u
                        where u.userId <> :userId and (:search is null or lower(u.username) like lower(concat ('%',:search,'%')))
        """)
    Page<GetAllUserByNameResponseDTO> findUserByUsernameExceptUserId(Pageable pageable,@Param("search") String search, @Param("userId") Long userId);
}
