package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.view.MemberView;

public class MemberController {

    private MemberService memberService = new MemberService();
    private MemberView memberView = new MemberView();

    public void signUp() {


        MemberSignUpRequest request = memberView.getSignUpInput();


        memberService.signUp(request);
    }
}