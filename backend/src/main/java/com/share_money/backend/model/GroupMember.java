    package com.share_money.backend.model;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "group_member")
    @Builder
    @Getter
    @Setter
    public class GroupMember {

        @EmbeddedId
        private GroupMemberId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("memberId")
        @JoinColumn(name = "member_id")
        private User member;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("groupId")
        @JoinColumn(name = "group_id")
        private Group group;

        @Enumerated(EnumType.STRING)
        private MemberRole role;

    }
