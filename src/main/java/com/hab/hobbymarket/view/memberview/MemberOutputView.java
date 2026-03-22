package com.hab.hobbymarket.view.memberview;

public class MemberOutputView {

    public void printSignUpSuccess() {
        System.out.println("회원가입이 완료되었습니다!");
    }

    public void printSignUpFail(String message) {
        System.out.println("회원가입 실패: " + message);
    }

    public void printDeleteSuccess() {
        System.out.println("회원 탈퇴 완료");
    }

    public void printDeleteFail() {
        System.out.println("이미 탈퇴했거나 존재하지 않는 회원입니다.");
    }

    public void printDeleteCanceled() {
        System.out.println("탈퇴가 취소되었습니다.");
    }
}
