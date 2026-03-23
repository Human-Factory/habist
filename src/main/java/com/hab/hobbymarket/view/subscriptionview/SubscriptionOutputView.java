package com.hab.hobbymarket.view.subscriptionview;

import com.hab.hobbymarket.model.Subscription;

public class SubscriptionOutputView {
    // 현재 구독 정보 출력
    public void showSubscription(Subscription subscription) {

        System.out.println("\n========== 내 구독 정보 ==========");

        // 구독 정보가 없으면 안내 문구 출력
        if (subscription == null) {
            System.out.println("현재 활성 구독이 없습니다.");
            return;
        }

        // 구독 정보 출력
        System.out.println("구독번호 : " + subscription.getSubscriptionId());
        System.out.println("시작일 : " + subscription.getStartDate());
        System.out.println("종료일 : " + subscription.getEndDate());
        System.out.println("상태 : " + subscription.getStatus());
    }
}

