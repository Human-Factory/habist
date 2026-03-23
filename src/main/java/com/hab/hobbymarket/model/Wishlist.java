package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Wishlist {

    // 관심목록 PK
    private int wishlistId;

    // 어떤 회원의 관심목록인지 저장하는 회원 번호
    private int memberId;

    // 어떤 강의를 관심목록에 담았는지 저장하는 강의 번호
    private int lectureId;

    // 강의 제목
    // 조회할 때 JOIN 해서 같이 받아오면 화면 출력이 편해짐
    private String lectureTitle;

    // 관심목록에 추가된 시간
    private Timestamp createdAt;

    // 기본 생성자
    // 객체를 빈 상태로 먼저 만들고 set으로 값 넣을 때 사용
    public Wishlist(int wishlistId, int memberId, int lectureId, String title, java.sql.Timestamp createdAt) {
    }

    // 전체 필드에 넣을 생성자, getter 및 setter, toString() 사용
    public Wishlist(int wishlistId, int memberId, int lectureId, String lectureTitle, Timestamp createdAt) {
        this.wishlistId = wishlistId;
        this.memberId = memberId;
        this.lectureId = lectureId;
        this.lectureTitle = lectureTitle;
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

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
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
                ", lectureTitle='" + lectureTitle + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}