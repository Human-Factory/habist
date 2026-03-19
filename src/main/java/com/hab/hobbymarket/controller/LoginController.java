package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.LoginService;

public class LoginController {

    private final LoginService loginService = new LoginService();

    // 로그인 요청 처리
    public boolean login(String id, String pwd) {
        return loginService.login(id, pwd);
    }

    // 로그아웃
    public void logout() {
        System.out.println("로그아웃 되었습니다.");
    }
}