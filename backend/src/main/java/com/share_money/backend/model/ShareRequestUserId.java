package com.share_money.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ShareRequestUserId implements Serializable {


    @Column(name = "share_request_id")
    private Long shareRequestId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShareRequestUserId that)) return false;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(shareRequestId, that.shareRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, shareRequestId);
    }





}
