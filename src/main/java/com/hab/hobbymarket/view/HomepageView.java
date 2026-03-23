package com.hab.hobbymarket.view;

import com.hab.hobbymarket.controller.ContentController;
import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class HomepageView {

    private static final Logger log = LoggerFactory.getLogger(HomepageView.class);
    LoginView loginView = new LoginView();
    ContentController content = new ContentController();
    Scanner sc = new Scanner(System.in);
    MypageView mypageView = new MypageView();

    public void displayHomePage() {

        while (true) {
            System.out.println("\n===========================");
            System.out.println("    🎯 하비스에 오신걸 환영합니다!");
            System.out.println("===========================");
            System.out.println("1. 강의 조회");
            System.out.println("2. 마이페이지");
            System.out.println("3. 로그아웃");
            System.out.println("0. 종료");
            System.out.println("===========================");
            System.out.print("메뉴를 선택해주세요 : ");

            String no = sc.nextLine().trim();

            switch (no) {
                case "1" ->
                    content.showLecturesByCategory();


                case "2" -> mypageView.displayMyPageMenu();

                case "3" -> {
                    System.out.println("로그아웃 합니다");
                    loginView.logout();
                    return;
                }

                case "0" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("🚨 올바른 번호를 입력해주세요.");
            }
        }

    }

}
