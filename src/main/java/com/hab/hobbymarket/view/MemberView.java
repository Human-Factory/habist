package com.hab.hobbymarket.view;

import com.hab.hobbymarket.model.MemberSignUpRequest;
import java.util.Scanner;

public class MemberView {

    private Scanner scanner = new Scanner(System.in);

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
}