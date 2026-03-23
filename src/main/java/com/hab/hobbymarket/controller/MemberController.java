package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.memberview.MemberOutputView;

public class MemberController {

    private final MemberService memberService;
    private final MemberInputView memberInputView;
    private final MemberOutputView memberOutputView = new MemberOutputView();

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.memberInputView = new MemberInputView(this);
    }
    
    public void signUp(MemberSignUpRequest request) {
        // Service에 넘기기
        memberService.signUp(request);
    }

    public void signUp() {
        // 1. View에서 입력값 받기
        MemberSignUpRequest request = memberInputView.getSignUpInput();

        // 2. Service에 넘기기
        memberService.signUp(request);
    }

    public void deleteMember() {
        if (!memberInputView.confirmDelete()) {
            System.out.println("회원 탈퇴가 취소되었습니다.");
            return;
        }

        if (SessionManager.getCurrentUser() == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }

        Long memberId = SessionManager.getCurrentUser().getMemberId();
        boolean result = memberService.deleteMember(memberId.intValue());

        if (result) {
            System.out.println("회원 탈퇴 완료");
            SessionManager.clear();
        } else {
            System.out.println("회원 탈퇴에 실패했습니다.");
        }
    }
}
