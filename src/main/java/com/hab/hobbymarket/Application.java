package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.view.LoginView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController();
        LoginView loginView = new LoginView(sc);

        while (true) {
            System.out.println("\n===== HABIS 메뉴 =====");
            System.out.println("1. 회원가입");
            System.out.println("2. 회원 탈퇴");
            System.out.println("3. 로그인");
            System.out.println("4. 로그아웃");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            String menu = sc.nextLine();

            switch (menu) {
                case "1":
                    memberController.signUp();
                    break;
                case "2":
                    memberController.deactivateMember();
                    break;
                case "3":
                    loginView.login();
                    break;
                case "4":
                    loginView.logout();
                    break;
                case "0":
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}