package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.SubscriptionDAO;
import com.hab.hobbymarket.model.Subscription;

import java.sql.Connection;
import java.time.LocalDate;

public class SubscriptionService {
    // DAO 객체
    private final SubscriptionDAO subscriptionDAO;

    // 생성자에서 DAO 생성
    public SubscriptionService(Connection con) {
        this.subscriptionDAO = new SubscriptionDAO(con);
    }

    // ------------------------------------------------------------------
    // 구독 신청
    // 오늘부터 1개월 뒤까지 구독 기간 설정
    // ------------------------------------------------------------------
    public boolean subscribe(int memberId) {

        // 시작일 = 오늘
        LocalDate startDate = LocalDate.now();

        // 종료일 = 한 달 뒤
        LocalDate endDate = startDate.plusMonths(1);

        // DAO에 저장 요청
        return subscriptionDAO.insertSubscription(memberId, startDate, endDate);
    }

    // ------------------------------------------------------------------
    // 현재 활성 구독 조회
    // ------------------------------------------------------------------
    public Subscription getMyActiveSubscription(int memberId) {
        return subscriptionDAO.findActiveSubscriptionByMemberId(memberId);
    }

    // ------------------------------------------------------------------
    // 구독 취소
    // ------------------------------------------------------------------
    public boolean cancelSubscription(int memberId) {
        return subscriptionDAO.cancelSubscription(memberId);
    }

}
