package com.share_money.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "share_requests")
public class ShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shareRequestId;

    private ShareRequestStatus shareRequestStatus;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private User shareOwner;

    @OneToMany(mappedBy = "shareRequest")
    private Set<ShareRequestUser> shareRequestUsers = new HashSet<ShareRequestUser>();
}
