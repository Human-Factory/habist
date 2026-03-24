package com.hab.hobbymarket.model;

import java.time.LocalDate;

public class Subscription {

   private int subscriptionId; // 구독 번호
   private int memberId; // 회원 번호
   private LocalDate startDate; // 구독 시작일
   private LocalDate endDate; // 구독 만료일
   private String status; // 구독 상태(active, cancle)

    public Subscription() {}

    // 구독 생성자 생성
    public Subscription(int subscriptionId, int memberId, LocalDate startDate, LocalDate endDate, String status) {
        this.subscriptionId = subscriptionId;
        this.memberId = memberId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionId=" + subscriptionId +
                ", memberId=" + memberId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}