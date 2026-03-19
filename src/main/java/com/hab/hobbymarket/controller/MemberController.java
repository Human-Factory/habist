package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.view.memberview.MemberInputView;

public class MemberController {
    private final MemberService memberService = new MemberService();

    public void deactivateMember() {

        // 사용자에게 회원 번호 입력 받기
        int memberId = MemberInputView.inputMemberId();

        // Service 호출해서 탈퇴 처리
        boolean result = memberService.deactivateMember(memberId);

        // 결과 출력
        if (result) {
            System.out.println("회원 탈퇴 완료 (INACTIVE 처리됨)");
        } else {
            System.out.println("회원 탈퇴 실패");
        }
    }
}
