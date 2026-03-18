package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Subscription {
    private int subscriptionId;
    private int memberId;
    private int instructorId;
    private Timestamp createdAt;

    public Subscription() {}

    public Subscription(int subscriptionId, int memberId, int instructorId, Timestamp createdAt) {
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionId=" + subscriptionId +
                ", memberId=" + memberId +
                ", instructorId=" + instructorId +
                ", createdAt=" + createdAt +
                '}';
    }
}
