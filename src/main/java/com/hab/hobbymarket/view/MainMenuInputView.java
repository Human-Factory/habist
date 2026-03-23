package com.hab.hobbymarket.view;

import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;

import java.util.Scanner;

public class MainMenuInputView {

    private MemberInputView memberInputView;
    private EnrollmentInputView enrollmentInputView;
    private SubscriptionInputView subscriptionInputView;
    private WishlistInputView wishlistInputView;
    private LoginView loginView;
    private Scanner sc;
    private MemberController memberController;

    public MainMenuInputView(
            MemberInputView memberInputView,
            EnrollmentInputView enrollmentInputView,
            SubscriptionInputView subscriptionInputView,
            WishlistInputView wishlistInputView,
            LoginView loginView,
            Scanner sc,
            MemberController memberController
    ) {
        this.memberInputView = memberInputView;
        this.enrollmentInputView = enrollmentInputView;
        this.subscriptionInputView = subscriptionInputView;
        this.wishlistInputView = wishlistInputView;
        this.loginView = loginView;
        this.sc = sc;
        this.memberController = memberController;
    }

    public MainMenuInputView(MemberInputView memberInputView, EnrollmentInputView enrollmentInputView, SubscriptionInputView subscriptionInputView, WishlistInputView wishlistInputView, LoginView loginView) {

    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n===== HABIS 메뉴 =====");

            if (!SessionManager.isLoggedIn()) {
                System.out.println("1. 회원가입");
                System.out.println("2. 회원 탈퇴");
                System.out.println("3. 로그인");
                System.out.println("0. 종료");
                System.out.print("선택: ");

                String input = sc.nextLine();

                switch (input) {
                    case "1":
                        memberController.signUp();
                        break;
                    case "2":
                        memberController.deactivateMember();
                        break;
                    case "3":
                        loginView.login();
                        break;
                    case "0":
                        System.out.println("프로그램 종료");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }

            } else {
                System.out.println("1. 로그아웃");
                System.out.println("0. 종료");
                System.out.print("선택: ");

                String input = sc.nextLine();

                switch (input) {
                    case "1":
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
}