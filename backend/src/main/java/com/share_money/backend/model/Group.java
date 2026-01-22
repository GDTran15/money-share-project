package com.share_money.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groups")
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;

    private Integer numberOfMembers;

    private GroupPurpose groupPurpose;
    


    @OneToMany(mappedBy = "group")
    private Set<GroupMember> groupMembers = new HashSet<>();
}
