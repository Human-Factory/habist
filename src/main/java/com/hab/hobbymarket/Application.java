package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.ContentController;
import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.view.LoginView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController();
        LoginView loginView = new LoginView();
      
        ContentController controller = new ContentController();

        // LEC-001 테스트 : 카테고리 목록 조회
        controller.showCategory();

        // LEC-002 테스트 : 카테고리별 강의 목록 조회 (사용자 입력 포함)
        controller.showLecturesByCategory();

        while (true) {
            System.out.println("\n===== HABIS 메뉴 =====");
            System.out.println("1. 회원가입");
            System.out.println("2. 회원 탈퇴");
            System.out.println("3. 로그인");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int menu = sc.nextInt();

            switch (menu) {
                case 1:
                    memberController.signUp();
                    break;
                case 2:
                    memberController.deactivateMember();
                    break;
                case 3:
                    loginView.login();
                    break;
                case 0:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}