package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.ContentController;
import com.hab.hobbymarket.controller.MemberController;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // 사용자 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 회원 기능을 실행하기 위해 controller 객체 생성
        MemberController memberController = new MemberController();


        // 메뉴 출력
        while (true) {
            System.out.println("\n===== 메뉴 =====");
            System.out.println("1. 회원 탈퇴");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            // 번호 입력 받아 다음으로 넣어가지
            int menu = sc.nextInt();

            // 누른 번호에 따라 실행
            switch (menu) {

                //탈퇴 기능 실행
                case 1:
                    memberController.deactivateMember();
                    break;

                case 0:
                    System.out.println("프로그램 종료");
                    return;

                default:
                    System.out.println("잘못된 입력");
            }
        }

    }
}