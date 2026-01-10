package com.share_money.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "share_request_user")
public class ShareRequestUser {

    @EmbeddedId
    private ShareRequestUserId shareRequestUserId;

    @ManyToOne
    @MapsId("shareRequestId")
    @JoinColumn(name = "share_request_id")
    private ShareRequest shareRequest;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ShareRequestAnswer shareRequestAnswer;

}
