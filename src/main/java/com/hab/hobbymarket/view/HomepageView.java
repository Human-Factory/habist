package com.hab.hobbymarket.view;

import com.hab.hobbymarket.controller.ContentController;
import com.hab.hobbymarket.view.inquiryview.InquiryInputView;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.adminview.AdminInputView;
import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class HomepageView {

    public HomepageView(ContentController content,MypageView mypageView, InquiryInputView inquiryInputView) {
        this.content = content;
        this.mypageView = mypageView;
        this.inquiryInputView = inquiryInputView; // 받아서 저장
    }

    private static final Logger log = LoggerFactory.getLogger(HomepageView.class);
    LoginView loginView = new LoginView();
    ContentController content;
    MypageView mypageView;
    InquiryInputView inquiryInputView;
    Scanner sc = ScannerUtil.getInstance();

    // 사용자 페이지
    public void displayHomePage() {

        while (true) {
            System.out.println("\n===========================");
            System.out.println("    🎯 하비스에 오신걸 환영합니다!");
            System.out.println("===========================");
            System.out.println("1. 강의 조회");
            System.out.println("2. 마이페이지");
            System.out.println("3. 문의하기");
            System.out.println("4. 로그아웃");
            System.out.println("0. 종료");
            System.out.println("===========================");
            System.out.print("메뉴를 선택해주세요 : ");

            String no = sc.nextLine().trim();

            switch (no) {
                case "1" ->
                        content.showLecturesByCategory();

                case "2" -> mypageView.displayMyPageMenu();

                case "3" -> inquiryInputView.displayInquiryWrite();

                case "4" -> {
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

    // 관리자 페이지
    public void displayAdminMenu() {
        while (SessionManager.isLoggedIn()) {
            System.out.println("\n========== 관리자 페이지 ==========");
            System.out.println("1. 회원 관리");
            System.out.println("2. 공지사항 관리");
            System.out.println("3. Q&A 관리");
            System.out.println("4. 로그아웃");
            System.out.println("0. 메인으로");
            System.out.print("선택 : ");

            String menu = sc.nextLine().trim();

            switch (menu) {
                case "1" -> AdminInputView.showMemberManageMenu();
                case "2" -> AdminInputView.showNoticeMenu();
                case "3" -> AdminInputView.showQnaMenu();
                case "4" -> {
                    loginView.logout();
                    return;
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }

}
