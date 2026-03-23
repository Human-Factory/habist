package com.hab.hobbymarket.view.memberview;

import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.model.MemberSignUpRequest;
import com.hab.hobbymarket.model.PasswordResetRequest;

import java.util.Scanner;

public class MemberInputView {

    private MemberController memberController;
    private Scanner scanner = new Scanner(System.in);

    public MemberInputView(MemberController memberController) {
        this.memberController = memberController;
    }

    // 회원가입 전체 흐름 실행
    public void signUp() {
        MemberSignUpRequest request = getSignUpInput();
        memberController.signUp(request);
    }

    // 회원가입 입력
    public MemberSignUpRequest getSignUpInput() {

        System.out.print("아이디 입력: ");
        String loginId = scanner.nextLine();


        System.out.print("비밀번호 입력: ");
        String password = scanner.nextLine();

        System.out.print("닉네임 입력: ");
        String nickname = scanner.nextLine();

        System.out.print("이름 입력: ");
        String name = scanner.nextLine();

        System.out.print("이메일 입력: ");
        String email = scanner.nextLine();

        System.out.print("전화번호 입력: ");
        String phone = scanner.nextLine();

        return new MemberSignUpRequest(loginId, password, email, nickname, name, phone);
    }

    public boolean confirmDelete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정말 탈퇴하시겠습니까? (Y/N): ");
        return sc.nextLine().equalsIgnoreCase("Y");
    }

    // 비밀번호 재설정
    public PasswordResetRequest getPasswordResetInput() {

        // 비밀번호 재설정 화면 안내
        System.out.println("===== 비밀번호 재설정 =====");

        // 로그인 아이디 입력
        System.out.print("아이디: ");
        String loginId = scanner.nextLine();

        // 본인 확인용 이름 입력
        System.out.print("이름: ");
        String name = scanner.nextLine();

        // 새 비밀번호 입력
        System.out.print("새 비밀번호: ");
        String newPassword = scanner.nextLine();

        // 입력받은 값을 DTO에 담아서 반환
        return new PasswordResetRequest(loginId, name, newPassword);
    }
}