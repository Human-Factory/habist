package com.hab.hobbymarket.view;

import com.hab.hobbymarket.controller.InquiryController;
import com.hab.hobbymarket.controller.InstructorController;
import com.hab.hobbymarket.controller.MessageController;
import com.hab.hobbymarket.controller.WishlistController;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentInputView;
import com.hab.hobbymarket.view.inquiryview.InquiryInputView;
import com.hab.hobbymarket.view.memberview.MemberInputView;
import com.hab.hobbymarket.view.messageview.MessageInputView;
import com.hab.hobbymarket.view.myinformationview.MyInfomationInputView;

import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionInputView;
import com.sun.tools.javac.Main;

public class MypageView {
    Scanner sc = ScannerUtil.getInstance();
    private InstructorController instructorController;
    private WishlistController wishlistController;
    private MyInfomationInputView myInfomationInputView;
    private EnrollmentInputView enrollmentInputView;
    private MemberInputView memberInputView;
    private InquiryInputView inquiryInputView;
    private SubscriptionInputView subscriptionInputView;
    private MessageController messageController;

    public MypageView(MyInfomationInputView myInfomationInputView,
                      WishlistController wishlistController,
                      InstructorController instructorController,
                      EnrollmentInputView enrollmentInputView,
                      MemberInputView memberInputView,
                      InquiryInputView inquiryInputView,
                      SubscriptionInputView subscriptionInputView,
                      MessageController messageController) {
        this.wishlistController = wishlistController;
        this.myInfomationInputView = myInfomationInputView;
        this.instructorController = instructorController;
        this.enrollmentInputView = enrollmentInputView;
        this.memberInputView = memberInputView;
        this.inquiryInputView = inquiryInputView;
        this.subscriptionInputView = subscriptionInputView;
        this.messageController = messageController;
    }

    public void displayMyPageMenu() {

        while (true) {
            // 세션이 끊기면 마이페이지 종료
            if (!SessionManager.isLoggedIn()) {
                return;
            }
            // 내 정보 출력 (OutputView 역할)
            Member member = SessionManager.getCurrentUser();

            // Null이면 종료
            if (member == null) {
                return;
            }

            System.out.println(member.getNickname() + "님의 마이페이지");

            // 메뉴 출력 + 입력 (InputView 역할)
            System.out.println("1. ❤️구독하기!!!❤️");
            System.out.println("2. 내 정보");
            System.out.println("3. 관심 목록");
            System.out.println("4. 학습 목록");
            System.out.println("5. 문의 목록");
            System.out.println("6. 쪽지 목록");
            System.out.println("7. 피드백 목록");
            System.out.println("8. 강사 신청");
            System.out.println("9. 회원 탈퇴");
            System.out.println("0. 뒤로가기");
            System.out.println("=============================");
            System.out.println("메뉴를 선택해 주세요: ");
            String no = sc.nextLine();

            switch (no) {
                case "1" -> subscriptionInputView.showSubscriptionMenu();

                case "2" -> {
                    System.out.println("아이디   : " + member.getLoginId());
                    System.out.println("닉네임   : " + member.getNickname());
                    System.out.println("이름     : " + member.getName());
                    System.out.println("이메일   : " + member.getEmail());
                    System.out.println("전화번호 : " + member.getPhone());
                    System.out.println("1. 내 정보 수정");
                    System.out.println("0. 뒤로 가기");
                    String no1 = sc.nextLine();

                    if (no1.equals("1")) {
                        myInfomationInputView.myinfomodify();
                    }else {
                        return;
                    }
                }
                case "3" -> wishlistController.showMyWishlist();

                case "4" -> enrollmentInputView.showLearningMenu();

                case "5" -> inquiryInputView.displayInquiryMenu();

                case "6" -> messageController.start();

                case "8" -> instructorController.apply(member.getMemberId());

                case "9" -> {
                    boolean deleted = memberInputView.deleteMember();
                    if (deleted) {
                        return;
                    }
                }
                case "0" -> {return;}
            }
        }

    }
}
