package com.hab.hobbymarket.model;

import java.sql.Timestamp;

public class Wishlist {

    private int wishlistId;
    private int memberId;
    private int lectureId;
    private Timestamp createdAt;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, int memberId, int lectureId, Timestamp createdAt) {
        this.wishlistId = wishlistId;
        this.memberId = memberId;
        this.lectureId = lectureId;
        this.createdAt = createdAt;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
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
                "wishlistId=" + wishlistId +
                ", memberId=" + memberId +
                ", lectureId=" + lectureId +
                ", createdAt=" + createdAt +
                '}';
    }
}