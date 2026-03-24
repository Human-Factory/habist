package com.hab.hobbymarket.model;

import java.sql.Timestamp;

public class Notice {

    // 공지 번호
    private int noticeId;

    // 공지 제목
    private String title;

    // 공지 내용
    private String content;

    // 등록일
    private Timestamp createdAt;

    // 기본 생성자
    public Notice() {
    }

    // 전체 필드 생성자
    public Notice(int noticeId, String title, String content, Timestamp createdAt) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}