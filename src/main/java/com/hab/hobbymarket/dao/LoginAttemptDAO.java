package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.LoginAttempt;

public interface LoginAttemptDAO {

    // 로그인 시도 조회 (없으면 null)
    LoginAttempt findByLoginId(String loginId);

    // 최초 로그인 시도 생성
    void insert(String loginId);

    // 실패 횟수 증가
    void increaseAttemptCount(String loginId, int attemptCount);

    // 잠금 설정
    void updateLock(String loginId, String lockedUntil);

    // 로그인 성공 시 초기화
    void reset(String loginId);
}