package com.hab.hobbymarket;

<<<<<<< HEAD
import com.hab.hobbymarket.controller.WishlistController;
import com.hab.hobbymarket.model.Wishlist;

import java.util.List;
=======
import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.controller.EnrollmentController;
import com.hab.hobbymarket.controller.MemberController;
import com.hab.hobbymarket.controller.SubscriptionController;
import com.hab.hobbymarket.controller.WishlistController;
import com.hab.hobbymarket.service.EnrollmentService;
import com.hab.hobbymarket.service.MemberService;
import com.hab.hobbymarket.service.SubscriptionService;
import com.hab.hobbymarket.service.WishlistService;
import com.hab.hobbymarket.view.LoginView;
import com.hab.hobbymarket.view.MainMenuInputView;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;

import java.sql.Connection;
>>>>>>> ea1729a79b358d43cc1ab27c8bf2d6c7cb03e929

public class Application {

    public static void main(String[] args) {

<<<<<<< HEAD
        // Controller 생성
        WishlistController controller = new WishlistController();

        // 테스트용 memberId (DB에 있는 값)
        int memberId = 1;

        // 관심목록 조회
        List<Wishlist> list = controller.findByMemberId(memberId);

        // 출력
        if (list.isEmpty()) {
            System.out.println("관심목록이 없습니다.");
        } else {
            for (Wishlist w : list) {
                System.out.println(w);
            }
=======
        // 1. DB 연결
        Connection con = DBConnection.getConnection();
        if (con == null) {
            System.out.println("🚨 프로그램을 종료합니다.");
            return;
>>>>>>> ea1729a79b358d43cc1ab27c8bf2d6c7cb03e929
        }
        System.out.println("✅ DB 연결 성공!");

        // 2. Member 조립 =============================
        MemberService memberService = new MemberService();
        MemberController memberController = new MemberController(memberService);
        MemberInputView memberInputView = new MemberInputView(memberController);
        // ============================================

        // 3. Login 조립 ==============================
        LoginView loginView = new LoginView();
        // ============================================

        // 3. Enrollment 조립 =========================
        EnrollmentService enrollmentService = new EnrollmentService(con);
        EnrollmentController enrollmentController = new EnrollmentController(enrollmentService);
        EnrollmentInputView enrollmentInputView = new EnrollmentInputView(enrollmentController);
        // ============================================

        // 4. Subscription 조립 =======================
        SubscriptionService subscriptionService = new SubscriptionService(con);
        SubscriptionController subscriptionController = new SubscriptionController(subscriptionService);
        SubscriptionInputView subscriptionInputView = new SubscriptionInputView(subscriptionController);
        // ============================================

        // 5. Wishlist 조립 ===========================
        WishlistService wishlistService = new WishlistService(con);
        WishlistController wishlistController = new WishlistController(wishlistService);
        WishlistInputView wishlistInputView = new WishlistInputView(wishlistController);
        // ============================================

        // 6. MainMenu 조립 (항상 가장 마지막!)
        MainMenuInputView mainMenuInputView = new MainMenuInputView(
                memberInputView,
                enrollmentInputView,
                subscriptionInputView,
                wishlistInputView
        );

        // 7. 첫 화면 호출
        mainMenuInputView.displayMainMenu();

        // 8. 종료 시 DB 닫기
        DBConnection.close(con);
    }

}