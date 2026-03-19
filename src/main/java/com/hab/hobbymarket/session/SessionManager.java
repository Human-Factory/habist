package com.hab.hobbymarket.session;

import com.hab.hobbymarket.model.Member;

public class SessionManager {

    // 현재 로그인한 사용자 (없으면 null)
    private static Member currentUser;

    // 로그인 시 사용자 저장
    public static void setCurrentUser(Member member) {
        currentUser = member;
    }

    // 현재 로그인 사용자 반환
    public static Member getCurrentUser() {
        return currentUser;
    }

    // 로그아웃 (세션 초기화)
    public static void clear() {
        currentUser = null;
    }

    // 로그인 여부 확인
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
