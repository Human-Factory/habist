package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Subscription {

    // Subscription 변수 생성
    private int subscriptionId;
    private int memberId;
    private int instructorId;
    private Timestamp createdAt; // 날짜/ 시간 컬럼 생성


    // Subscription의 생성자, getter 및 setter, toString() 생성
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
        return "Subscription{" +
                "subscriptionId=" + subscriptionId +
                ", memberId=" + memberId +
                ", instructorId=" + instructorId +
                ", createdAt=" + createdAt +
                '}';
    }
}
