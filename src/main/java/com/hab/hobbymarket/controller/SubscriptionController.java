package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Subscription;
import com.hab.hobbymarket.service.SubscriptionService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.subscriptionview.SubscriptionOutputView;

public class SubscriptionController {

    // 서비스 객체
    private final SubscriptionService subscriptionService;

    // 출력 전용 뷰
    private final SubscriptionOutputView subscriptionOutputView = new SubscriptionOutputView();

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // ------------------------------------------------------------------
    // 구독 신청
    // 로그인한 회원 기준으로 구독 처리
    // ------------------------------------------------------------------
    public void subscribeApp() {

        // 로그인 여부 확인
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인한 회원 가져오기
        Member currentUser = SessionManager.getCurrentUser();

        // 회원 번호 추출
        int memberId = currentUser.getMemberId().intValue();

        // 서비스에 구독 요청
        boolean result = subscriptionService.subscribe(memberId);

        // 결과 출력
        if (result) {
            System.out.println("구독이 완료되었습니다.");
            System.out.println("현 시점부터 다음 달까지 이용 가능합니다.");
        } else {
            System.out.println("이미 구독 중이거나 구독 처리에 실패했습니다.");
        }
    }

    // ------------------------------------------------------------------
    // 내 구독 조회
    // ------------------------------------------------------------------
    public void showMySubscription() {

        // 로그인 여부 확인
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인 회원
        Member currentUser = SessionManager.getCurrentUser();

        // 회원 번호
        int memberId = currentUser.getMemberId().intValue();

        // 현재 활성 구독 조회
        Subscription subscription = subscriptionService.getMyActiveSubscription(memberId);

        // 출력 뷰에 전달
        subscriptionOutputView.showSubscription(subscription);
    }

    // ------------------------------------------------------------------
    // 구독 취소
    // ------------------------------------------------------------------
    public void cancelMySubscription() {

        // 로그인 여부 확인
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인 회원
        Member currentUser = SessionManager.getCurrentUser();

        // 회원 번호
        int memberId = currentUser.getMemberId().intValue();

        // 서비스에 구독 취소 요청
        boolean result = subscriptionService.cancelSubscription(memberId);

        if (result) {
            System.out.println("구독이 취소되었습니다.");
        } else {
            System.out.println("현재 활성 구독이 없습니다.");
        }
    }
}
