package com.hab.hobbymarket.view;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.adminview.AdminInputView;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;
import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class MainMenuInputView {

    private MemberInputView memberInputView;
    private EnrollmentInputView enrollmentInputView;
    private SubscriptionInputView subscriptionInputView;
    private WishlistInputView wishlistInputView;
    private LoginView loginView;
    private HomepageView homepageView;
    private Scanner sc = ScannerUtil.getInstance();

    public MainMenuInputView(MemberInputView memberInputView,
                             EnrollmentInputView enrollmentInputView,
                             SubscriptionInputView subscriptionInputView,
                             WishlistInputView wishlistInputView,
                             LoginView loginView,
                             HomepageView homepageView) {
        this.memberInputView = memberInputView;
        this.enrollmentInputView = enrollmentInputView;
        this.subscriptionInputView = subscriptionInputView;
        this.wishlistInputView = wishlistInputView;
        this.loginView = loginView;
        this.homepageView = homepageView;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n===========================");
            System.out.println("    🎯 하비스에 오신걸 환영합니다!");
            System.out.println("===========================");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 비밀번호를 잃어버렸어요");
            System.out.println("0. 종료");
            System.out.println("===========================");
            System.out.print("메뉴를 선택해주세요 : ");

            String no = sc.nextLine().trim();

            switch (no) {
                case "1" -> {
                    boolean isLoggedIn = loginView.login();
                    if (!isLoggedIn){
                        break;
                    }
                    if (SessionManager.getCurrentUser().getRole().equals(Member.ROLE_ADMIN)) {
                        System.out.println("관리자 페이지로 이동합니다.");
                        homepageView.displayAdminMenu();
                    } else {
                        System.out.println("메인 페이지로 이동합니다.");
                        homepageView.displayHomePage();
                    }
                }

                case "2" ->
                    memberInputView.signUp();

                case "3" ->
                    memberInputView.getPasswordResetInput();

                case "0" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("🚨 올바른 번호를 입력해주세요.");
            }
        }
    }

}
