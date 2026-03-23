package com.hab.hobbymarket.model;

public class PasswordResetRequest {

    // 사용자가 입력한 로그인 아이디
    private String loginId;

    // 본인 확인용 이름
    private String name;

    // 새로 변경할 비밀번호
    private String newPassword;

    // 생성자
    public PasswordResetRequest(String loginId, String name, String newPassword) {
        this.loginId = loginId;
        this.name = name;
        this.newPassword = newPassword;
    }

    // getter
    public String getLoginId() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public String getNewPassword() {
        return newPassword;
    }
}