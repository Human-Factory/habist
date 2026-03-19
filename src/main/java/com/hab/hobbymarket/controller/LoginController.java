package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.LoginResult;
import com.hab.hobbymarket.service.LoginService;
import com.hab.hobbymarket.session.SessionManager;

public class LoginController {

    private final LoginService loginService = new LoginService();

    // 로그인 요청 처리
    public LoginResult login(String id, String pwd) {
        return loginService.login(id, pwd);
    }

    // 로그아웃 요청 처리
    public void logout() {
        SessionManager.clear();
        System.out.println("로그아웃 되었습니다.");
    }
}