package com.hab.hobbymarket;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.controller.*;
import com.hab.hobbymarket.dao.AdminDAO;
import com.hab.hobbymarket.dao.MessageDAO;
import com.hab.hobbymarket.service.*;
import com.hab.hobbymarket.view.HomepageView;
import com.hab.hobbymarket.view.LoginView;
import com.hab.hobbymarket.view.MainMenuInputView;
import com.hab.hobbymarket.view.MypageView;
import com.hab.hobbymarket.view.adminview.AdminInputView;
import com.hab.hobbymarket.view.contentview.ContentInputView;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.inquiryview.InquiryInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.messageview.MessageInputView;
import com.hab.hobbymarket.view.messageview.MessageOutputView;
import com.hab.hobbymarket.view.myinformationview.MyInfomationInputView;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.hab.hobbymarket.view.wishlistview.WishlistInputView;

import java.sql.Connection;

public class Application {

    public static void main(String[] args) {

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
            // 7. 내 정보 수정 기능 조립
            // ============================================================
            // 정보 수정 관련 기능
            MyInformationService myInformationService = new MyInformationService(con);
            MyInformationController myInformationController = new MyInformationController(myInformationService);
            MyInfomationInputView myInfomationInputView = new MyInfomationInputView(myInformationController);

            // ============================================================
            // 8. Content 기능 조립
            // ============================================================
            // 강의 조회 관련 기능
            // Service 없이 Controller만 생성!
            ContentController contentController = new ContentController();
            ContentInputView contentInputView = new ContentInputView();

            // 9. Instructor 조립 ============================
            InstructorController instructorController = new InstructorController();

            // 10. Comment 조립 ============================
            CommentsController commentsController = new CommentsController();

            // 11. Inquiry 조립 =============================
            InquiryService inquiryService = new InquiryService();
            InquiryController inquiryController = new InquiryController(inquiryService);
            InquiryInputView inquiryInputView = new InquiryInputView(inquiryController);

            AdminDAO adminDAO = new AdminDAO(con);
            AdminService adminService = new AdminService(adminDAO);
            AdminController adminController = new AdminController(adminService);
            AdminInputView adminInputView = new AdminInputView(adminController);

            // ============================================================
            // 1. 기초 부품 생성 (Service, InputView, OutputView)
            // ============================================================
            MessageService messageService = new MessageService(); // DAO 등을 인자로 받을 수 있음
            MessageInputView messageInputView = new MessageInputView();
            MessageOutputView messageOutputView = new MessageOutputView();

            // ============================================================
            // 2. 중간 관리자 생성 (Controller)
            // ============================================================
            // Controller는 일을 시킬 대상(Input, Output)과 로직(Service)을 모두 가집니다.
            MessageController messageController = new MessageController(
                    messageService,
                    messageInputView,
                    messageOutputView);



            // ============================================================
            // 12. 마이페이지 조립
            // ============================================================
            MypageView mypageView = new MypageView(
                    myInfomationInputView,
                    wishlistController,
                    instructorController,
                    enrollmentInputView,
                    memberInputView,
                    inquiryInputView,
                    subscriptionInputView,
                    messageController);

            // ============================================================
            // 13. 홈페이지 조립
            // ============================================================
            HomepageView homepageView = new HomepageView(contentController, mypageView, inquiryInputView);



            // ============================================================
            // 14. 메인 메뉴 조립
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
            // 15. 프로그램 시작
            // ============================================================
            mainMenuInputView.displayMainMenu();

        } finally {
            // ============================================================
            // 16. 프로그램 종료 시 DB 연결 닫기!
            // ============================================================
            DBConnection.close(con);
        }
    }

}