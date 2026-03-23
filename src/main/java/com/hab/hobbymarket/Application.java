package com.hab.hobbymarket;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.controller.*;
import com.hab.hobbymarket.service.EnrollmentService;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.service.SubscriptionService;
import com.hab.hobbymarket.service.WishlistService;
import com.hab.hobbymarket.view.HomepageView;
import com.hab.hobbymarket.view.LoginView;
import com.hab.hobbymarket.view.MainMenuInputView;
import com.hab.hobbymarket.view.contentview.ContentInputView;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;

import java.sql.Connection;

public class Application {

    public static void main(String[] args) {

//        // 1. DB 연결
//        Connection con = DBConnection.getConnection();
//        if (con == null) {
//            System.out.println("🚨 프로그램을 종료합니다.");
//            return;
//        }
//        System.out.println("✅ DB 연결 성공!");
//
//        // 2. Member 조립 =============================
//        MemberService memberService = new MemberService();
//        MemberController memberController = new MemberController(memberService);
//        MemberInputView memberInputView = new MemberInputView(memberController);
//        // ============================================
//
//        // 3. Login 조립 ==============================
//        LoginView loginView = new LoginView();
//        // ============================================
//
//        // 3. Enrollment 조립 =========================
//        EnrollmentService enrollmentService = new EnrollmentService(con);
//        EnrollmentController enrollmentController = new EnrollmentController(enrollmentService);
//        EnrollmentInputView enrollmentInputView = new EnrollmentInputView(enrollmentController);
//        // ============================================
//
//        // 4. Subscription 조립 =======================
//        SubscriptionService subscriptionService = new SubscriptionService(con);
//        SubscriptionController subscriptionController = new SubscriptionController(subscriptionService);
//        SubscriptionInputView subscriptionInputView = new SubscriptionInputView(subscriptionController);
//        // ============================================
//
//        // 5. Wishlist 조립 ===========================
//        WishlistService wishlistService = new WishlistService(con);
//        WishlistController wishlistController = new WishlistController(wishlistService);
//        WishlistInputView wishlistInputView = new WishlistInputView(wishlistController);
//        // ============================================
//
//        // 6. MainMenu 조립 (항상 가장 마지막!)
//
//        MainMenuInputView mainMenuInputView = new MainMenuInputView(
//                memberInputView,
//                enrollmentInputView,
//                subscriptionInputView,
//                wishlistInputView,
//                loginView
//        );
//
//        // 7. 첫 화면 호출
//        mainMenuInputView.displayMainMenu();
//
//        // 8. 종료 시 DB 닫기
//        DBConnection.close(con);
//    }
        // ============================================================
        // 1. DB 연결
        // ============================================================
        // DBConnection에서 Connection 객체를 가져온다.
        // 이후 Enrollment / Subscription / Wishlist 쪽 Service에서 이 연결을 사용한다.
        Connection con = DBConnection.getConnection();

        // DB 연결이 실패하면 더 진행할 수 없으므로 프로그램 종료
        if (con == null) {
            System.out.println("DB 연결 실패로 프로그램을 종료합니다.");
            return;
        }

        System.out.println("✅ DB 연결 성공!");

        try {
            // ============================================================
            // 2. Member 기능 조립
            // ============================================================
            // 회원가입 관련 기능은 현재 MemberService -> MemberController -> MemberInputView 구조로 연결
            MemberService memberService = new MemberService();
            MemberController memberController = new MemberController(memberService);
            MemberInputView memberInputView = new MemberInputView(memberController);

            // ============================================================
            // 3. Login 기능 조립
            // ============================================================
            // 로그인은 LoginView 내부에서 LoginController를 직접 사용하고 있음
            LoginView loginView = new LoginView();

            // ============================================================
            // 4. Enrollment 기능 조립
            // ============================================================
            // 수강신청/등록 관련 기능
            EnrollmentService enrollmentService = new EnrollmentService(con);
            EnrollmentController enrollmentController = new EnrollmentController(enrollmentService);
            EnrollmentInputView enrollmentInputView = new EnrollmentInputView(enrollmentController);

            // ============================================================
            // 5. Subscription 기능 조립
            // ============================================================
            // 구독 관련 기능
            SubscriptionService subscriptionService = new SubscriptionService(con);
            SubscriptionController subscriptionController = new SubscriptionController(subscriptionService);
            SubscriptionInputView subscriptionInputView = new SubscriptionInputView(subscriptionController);

            // ============================================================
            // 6. Wishlist 기능 조립
            // ============================================================
            // 관심목록 관련 기능
            // 현재 로그인한 사용자 기준으로 조회/삭제를 처리하게 될 흐름
            WishlistService wishlistService = new WishlistService(con);
            WishlistController wishlistController = new WishlistController(wishlistService);
            WishlistInputView wishlistInputView = new WishlistInputView(wishlistController);

            // ============================================================
            // 7. Content 기능 조립
            // ============================================================
            // 강의 조회 관련 기능
            // Service 없이 Controller만 생성!
            ContentController contentController = new ContentController();
            ContentInputView contentInputView = new ContentInputView();

            // ============================================================
            // 8. 홈페이지 조립
            // ============================================================
            HomepageView homepageView = new HomepageView();

            // ============================================================
            // 9. 메인 메뉴 조립
            // ============================================================
            // 메인 메뉴에서 회원가입 / 로그인 / 관심목록 메뉴 진입 흐름을 담당
            MainMenuInputView mainMenuInputView = new MainMenuInputView(
                    memberInputView,
                    enrollmentInputView,
                    subscriptionInputView,
                    wishlistInputView,
                    loginView,
                    homepageView
            );

            // ============================================================
            // 10. 프로그램 시작
            // ============================================================
            mainMenuInputView.displayMainMenu();

        } finally {
            // ============================================================
            // 11. 프로그램 종료 시 DB 연결 닫기
            // ============================================================
            DBConnection.close(con);
        }
    }

}