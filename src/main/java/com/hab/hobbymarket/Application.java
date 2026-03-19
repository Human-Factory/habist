package com.hab.hobbymarket;

import com.hab.hobbymarket.view.LoginView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LoginView loginView = new LoginView();

        while (true) {

            System.out.println("===== HABIS =====");
            System.out.println("1. 로그인");
            System.out.println("2. 종료");
            System.out.print("선택: ");

            String input = sc.nextLine();

            if ("1".equals(input)) {
                loginView.login();   // 👉 여기 핵심
            }
            else if ("2".equals(input)) {
                System.out.println("프로그램 종료");
                break;
            }
            else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}