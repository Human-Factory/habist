package com.hab.hobbymarket.view.memberview;

import java.util.Scanner;

public class MemberInputView {
    public static int inputMemberId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("탈퇴할 회원 ID 입력: ");
        return sc.nextInt();
    }
}
