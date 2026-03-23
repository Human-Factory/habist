package com.hab.hobbymarket.model;

import java.time.LocalDateTime;

public class Inquiry {

    // 필드
    private Long inquiryId;
    private Long memberId;
    private String title;
    private String content;
    private String answer;
    private boolean isAnswered;
    private LocalDateTime createdAt;
    private LocalDateTime answeredAt;

    // 작성용 생성자
    public Inquiry(Long memberId, String title, String content) {
        this.memberId   = memberId;
        this.title      = title;
        this.content    = content;
        this.isAnswered = false;
        this.createdAt  = LocalDateTime.now();
    }

    // DB 조회용 생성자
    public Inquiry(Long inquiryId, Long memberId, String title, String content,
                   String answer, boolean isAnswered,
                   LocalDateTime createdAt, LocalDateTime answeredAt) {
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
    public Long getInquiryId()           { return inquiryId; }
    public Long getMemberId()            { return memberId; }
    public String getTitle()             { return title; }
    public String getContent()           { return content; }
    public String getAnswer()            { return answer; }
    public boolean isAnswered()          { return isAnswered; }
    public LocalDateTime getCreatedAt()  { return createdAt; }
    public LocalDateTime getAnsweredAt() { return answeredAt; }
}
