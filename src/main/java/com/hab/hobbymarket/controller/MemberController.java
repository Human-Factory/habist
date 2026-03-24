package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.model.PasswordResetRequest;
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

    public boolean deleteMember() {
        Member currentUser = SessionManager.getCurrentUser();
        int memberId = SessionManager.getCurrentUser().getMemberId().intValue();
        boolean result = memberService.deleteMember(memberId);

        if (result) {
            System.out.println("회원 탈퇴 완료");
            SessionManager.clear();
            return true;
        } else {
            System.out.println("회원 탈퇴에 실패했습니다.");
            return false;
        }
    }

    // 비밀번호 변경
    public void resetPassword() {

        // 1. View에서 비밀번호 재설정 입력값 받기
        PasswordResetRequest request = memberInputView.getPasswordResetInput();

        // 2. Service로 전달해서 실제 비밀번호 변경 수행
        boolean result = memberService.resetPassword(
                request.getLoginId(),
                request.getName(),
                request.getNewPassword()
        );

        // 3. 결과 출력
        if (result) {
            System.out.println("비밀번호가 성공적으로 변경되었습니다.");
        } else {
            System.out.println("일치하는 회원 정보가 없거나 비밀번호 변경에 실패했습니다.");
        }
    }
}
