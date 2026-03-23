package com.hab.hobbymarket.view;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

import java.util.Scanner;

public class MypageView {
    Scanner sc = new Scanner(System.in);

    public void displayMyPageMenu() {

        while (true) {
            // 내 정보 출력 (OutputView 역할)
            Member member = SessionManager.getCurrentUser();
            System.out.println(member.getNickname() + "님의 마이페이지");

            // 메뉴 출력 + 입력 (InputView 역할)
            System.out.println("1. 내 정보");
            System.out.println("2. 관심 목록");
            System.out.println("3. 학습 목록");
            System.out.println("4. 문의 목록");
            System.out.println("5. 쪽지 목록");
            System.out.println("6. 피드백 목록");
            System.out.println("0. 뒤로가기");
            System.out.println("=============================");
            System.out.println("메뉴를 선택해 주세요: ");
            String no = sc.nextLine();

            switch (no) {
                case "1" -> {
                    System.out.println("아이디   : " + member.getLoginId());
                    System.out.println("닉네임   : " + member.getNickname());
                    System.out.println("이름     : " + member.getName());
                    System.out.println("이메일   : " + member.getEmail());
                    System.out.println("전화번호 : " + member.getPhone());
                }
                case "0" -> { return; }
            }
        }

    }
}
