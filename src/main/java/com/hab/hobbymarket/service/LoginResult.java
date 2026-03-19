package com.hab.hobbymarket.service;

public enum LoginResult {
    SUCCESS,               // 로그인 성공
    INVALID_CREDENTIAL,    // 아이디 또는 비밀번호 오류
    LOCKED,                // 계정 잠금
    INACTIVE               // 비활성 계정
}