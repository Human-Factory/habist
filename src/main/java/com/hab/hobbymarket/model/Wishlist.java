package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Wishlist {
    private int subscriptionId;
    private int memberId;
    private int instructorId;
    private Timestamp createdAt;

    public Wishlist(int subscriptionId, int memberId, int instructorId, Timestamp createdAt) {
        this.subscriptionId = subscriptionId;
        this.memberId = memberId;
        this.instructorId = instructorId;
        this.createdAt = createdAt;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "subscriptionId=" + subscriptionId +
                ", memberId=" + memberId +
                ", instructorId=" + instructorId +
                ", createdAt=" + createdAt +
                '}';
    }
}
