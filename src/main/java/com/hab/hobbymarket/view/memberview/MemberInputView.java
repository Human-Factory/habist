package com.hab.hobbymarket.view.memberview;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import java.util.Scanner;

public class MemberInputView {

    private Scanner scanner = new Scanner(System.in);

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

    // 회원 탈퇴용 ID 입력
    public static int inputMemberId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("탈퇴할 회원 ID 입력: ");
        return sc.nextInt();
    }
}