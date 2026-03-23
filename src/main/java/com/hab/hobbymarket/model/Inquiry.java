package com.hab.hobbymarket.model;

import java.sql.Timestamp;

public class Inquiry {

    // 필드
    private Long inquiryId;       // inquiry_id
    private Long memberId;        // member_id
    private String title;         // title
    private String content;       // content
    private String answer;        // answer
    private boolean isAnswered;   // is_answered
    private Timestamp createdAt;  // created_at
    private Timestamp answeredAt; // answered_at

    // DB 조회용 생성자
    public Inquiry(Long inquiryId, Long memberId, String title, String content,
                   String answer, boolean isAnswered,
                   Timestamp createdAt, Timestamp answeredAt) {
        this.inquiryId  = inquiryId;
        this.memberId   = memberId;
        this.title      = title;
        this.content    = content;
        this.answer     = answer;
        this.isAnswered = isAnswered;
        this.createdAt  = createdAt;
        this.answeredAt = answeredAt;
    }

    // Getter
    public Long getInquiryId()       { return inquiryId; }
    public Long getMemberId()        { return memberId; }
    public String getTitle()         { return title; }
    public String getContent()       { return content; }
    public String getAnswer()        { return answer; }
    public boolean isAnswered()      { return isAnswered; }
    public Timestamp getCreatedAt()  { return createdAt; }
    public Timestamp getAnsweredAt() { return answeredAt; }

    // Setter (수정 기능에서 사용)
    public void setTitle(String title)     { this.title = title; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "Inquiry{" +
                "inquiryId=" + inquiryId +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", isAnswered=" + isAnswered +
                ", createdAt=" + createdAt +
                '}';
    }
}
