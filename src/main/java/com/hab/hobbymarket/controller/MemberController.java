package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.service.MemberService;
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
    
    public void signUp() {
        // 1. View에서 입력값 받기
        MemberSignUpRequest request = memberInputView.getSignUpInput();

        // 2. Service에 넘기기
        memberService.signUp(request);
    }

    public void deleteMember(int memberId) {

        // Service 호출
        boolean result = memberService.deleteMember(memberId);

        // 결과 출력
        if (result) {
            System.out.println("회원 탈퇴 완료");
        } else {
            System.out.println("이미 탈퇴했거나 존재하지 않는 회원입니다.");
        }
    }
}    
