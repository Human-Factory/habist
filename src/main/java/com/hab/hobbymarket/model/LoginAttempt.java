package com.hab.hobbymarket.model;

import java.time.LocalDateTime;

public class LoginAttempt {

    private Long attemptId;
    private String loginId;
    private int attemptCount;
    private LocalDateTime lockedUntil;
    private LocalDateTime lastAttemptAt;

    // -----------------------------------------------
    // 생성자 (DB 조회용)
    // -----------------------------------------------
    public LoginAttempt(Long attemptId, String loginId, int attemptCount,
                        LocalDateTime lockedUntil, LocalDateTime lastAttemptAt) {
        this.attemptId = attemptId;
        this.loginId = loginId;
        this.attemptCount = attemptCount;
        this.lockedUntil = lockedUntil;
        this.lastAttemptAt = lastAttemptAt;
    }

    // -----------------------------------------------
    // Getter
    // -----------------------------------------------
    public Long getAttemptId() { return attemptId; }
    public String getLoginId() { return loginId; }
    public int getAttemptCount() { return attemptCount; }
    public LocalDateTime getLockedUntil() { return lockedUntil; }
    public LocalDateTime getLastAttemptAt() { return lastAttemptAt; }

    // -----------------------------------------------
    // Setter (필요한 것만)
    // -----------------------------------------------
    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    public void setLastAttemptAt(LocalDateTime lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
    }
}