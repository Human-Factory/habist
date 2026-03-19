package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.memberview.MemberOutputView;

public class MemberController {

    private MemberService memberService = new MemberService();
    private MemberInputView memberInputView = new MemberInputView();
    private MemberOutputView memberOutputView = new MemberOutputView();

    public void signUp() {
        // 1. View에서 입력값 받기
        MemberSignUpRequest request = memberInputView.getSignUpInput();

        // 2. Service에 넘기기
        memberService.signUp(request);
    }
}
