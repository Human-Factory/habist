package com.hab.hobbymarket.view.myinformationview;

import com.hab.hobbymarket.controller.MyInformationController;

import java.util.Scanner;

public class MyInfomationInputView {
    private MyInformationController myInformationController;
    Scanner sc = new Scanner(System.in);

    public MyInfomationInputView(MyInformationController myInformationController) {
        this.myInformationController = myInformationController;
    }

    public void myinfomodify() {

        // 1. 비밀번호 확인
        System.out.print("현재 비밀번호를 입력해주세요 : ");
        String password = sc.nextLine();

        // 2. Controller에 검증 요청
        boolean isVerified = myInformationController.verifyPassword(password);

        // 3. 틀리면 바로 return
        if (!isVerified) {
            System.out.println("🚨 비밀번호가 틀렸습니다.");
            return;
        }

        // 4. 맞으면 수정 메뉴 출력
        while (true) {
            System.out.println("1. 닉네임 수정");
            System.out.println("2. 이메일 수정");
            System.out.println("3. 전화번호 수정");
            System.out.println("4. 비밀번호 수정");
            System.out.println("0. 뒤로가기");

            switch (sc.nextLine()) {
                case "1" -> myInformationController.updateNickname();
                case "2" -> myInformationController.updateEmail();
                case "3" -> myInformationController.updatePhone();
                case "4" -> myInformationController.updatePwd();
                case "0" -> { return; }
            }
        }
    }
}
