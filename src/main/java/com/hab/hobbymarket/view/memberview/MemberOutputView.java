package com.hab.hobbymarket.view.memberview;

public class MemberOutputView {

    public void printSignUpSuccess() {
        System.out.println("회원가입이 완료되었습니다!");
    }

    public void printSignUpFail(String message) {
        System.out.println("회원가입 실패: " + message);
    }
}
