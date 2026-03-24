package com.hab.hobbymarket.model;


import java.sql.Timestamp;

public class Message {
    // 1. 필드
    private Long messageId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private boolean isRead;
    private Timestamp createdAt;

    // 2. 작성용 생성자 (보낼 때 사용)
    public Message(Long senderId, Long receiverId, String content) {
        this.senderId    = senderId;
        this.receiverId  = receiverId;
        this.content     = content;
        this.isRead      = false;
    }

    // 3. DB 조회용 생성자 (전체 필드)
    public Message(Long messageId, Long senderId, Long receiverId,
                   String content, boolean isRead, Timestamp createdAt) {
        this.messageId  = messageId;
        this.senderId   = senderId;
        this.receiverId = receiverId;
        this.content    = content;
        this.isRead     = isRead;
        this.createdAt  = createdAt;
    }

    // 4. Getter
    public Long getMessageId()     { return messageId; }
    public Long getSenderId()      { return senderId; }
    public Long getReceiverId()    { return receiverId; }
    public String getContent()     { return content; }
    public boolean isRead()        { return isRead; }
    public Timestamp getCreatedAt(){ return createdAt; }

    // 5. toString
    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                ", createdAt=" + createdAt +
                '}';
    }
}
