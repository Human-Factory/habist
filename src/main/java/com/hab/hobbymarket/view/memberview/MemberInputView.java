package com.hab.hobbymarket.view.memberview;

import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.model.MemberSignUpRequest;
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

    // Scanner 는 int 값을 입력 받게 되면 nextInt() 다음에 변수명.nextline() 을 한 번 하면 된다.

    // 회원가입 입력
    public MemberSignUpRequest getSignUpInput() {

        System.out.print("아이디 입력: ");
        String loginId = scanner.nextLine();
        scanner.nextLine();

        PrintPasswordPolicy();

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

    public void PrintPasswordPolicy(){
        System.out.println("비밀번호 조건");
        System.out.println("대소문자 구분");
        System.out.println("영어, 숫자, 특수문자(!@#$%^&*)");
    }
}
