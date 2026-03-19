package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.memberview.MemberOutputView;

public class MemberController {

    private MemberService memberService = new MemberService();
    private MemberInputView memberInputView = new MemberInputView();
    private MemberOutputView memberOutputView = new MemberOutputView();
    private MemberService memberService;

    public void signUp() {
        // 1. View에서 입력값 받기
        MemberSignUpRequest request = memberInputView.getSignUpInput();

        // 2. Service에 넘기기
        memberService.signUp(request);
    }

    public void deactivateMember() {
        // 사용자에게 회원 번호 입력 받기
        int memberId = MemberInputView.inputMemberId();

        // Service 호출해서 탈퇴 처리
        boolean result = memberService.deactivateMember(memberId);

        // 결과 출력
        if (result) {
            System.out.println("회원 탈퇴 완료");
        } else {
            System.out.println("이미 회원 탈퇴했거나 존재하지 않는 아아디입니다.");
        }
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
