package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.MemberService;

public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
