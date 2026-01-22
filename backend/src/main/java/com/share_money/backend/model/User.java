package com.share_money.backend.model;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
public  class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private String email;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "requester")
    private Set<Friendship> sentFriend = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<Friendship> receivedFriend = new HashSet<>();

    @OneToMany(mappedBy = "member")
    private Set<GroupMember> groupMembers = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "shareOwner")
    private Set<ShareRequest> shareRequests = new HashSet<>();

    @OneToMany(mappedBy = "shareReceiver")
    private Set<ShareRequest> shareRequestsReceiver = new HashSet<>();


    @OneToMany(mappedBy = "sender")
    private Set<FriendRequest> requestSender = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<FriendRequest> requestReceiver = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
