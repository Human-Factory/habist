package com.hab.hobbymarket.view.myinformationview;

import com.hab.hobbymarket.controller.MyInformationController;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

import java.util.Scanner;

public class MyInfomationInputView {
    private MyInformationController myInformationController;
    Scanner sc = new Scanner(System.in);

    public MyInfomationInputView(MyInformationController myInformationController) {
        this.myInformationController = myInformationController;
    }

    public void myinfomodify() {
        Member member = SessionManager.getCurrentUser();

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
                case "1" ->{
                    String nickname = member.getNickname();
                    System.out.println("변경할 닉네임을 입력해주십시오(현재 닉네임 :"+ nickname + ") : ");
                    String newNickname = sc.nextLine();
                    myInformationController.updateNickname(newNickname);
                }
                case "2" ->{
                    String email = member.getEmail();
                    System.out.println("변경할 이메일을 입력해주십시오(현재 이메일 :"+ email + ") : ");
                    String newEmail = sc.nextLine();
                    myInformationController.updateEmail(newEmail);
                }
                case "3" ->{
                    String phone = member.getPhone();
                    System.out.println("변경할 전화번호를 입력해주십시오(현재 전화번호 :"+ phone + ") : ");
                    String newPhone = sc.nextLine();
                    myInformationController.updatePhone(newPhone);
                }
                case "4" -> {
                    // password 는 비밀번호 검증 단계에서 이미 가져옴
                    System.out.println("변경할 비밀번호를 입력해주십시오(현재 비밀번호 :"+ password + ") : ");
                    String newPassword = sc.nextLine();
                    myInformationController.updatePassword(newPassword);
                }
                case "0" -> { return; }
            }
        }
    }
}
