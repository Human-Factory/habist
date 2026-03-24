package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Subscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class SubscriptionDAO {

    private final Connection con; // DB 호출


    // 객체 con 주입
    public SubscriptionDAO(Connection con) {
        this.con = con;
    }


    // 회원이 이미 구독 중인지 확인하는 메서드
    public boolean ActiveSubscription(int memberId) {
        String sql = "SELECT COUNT(*) FROM subscriptions WHERE member_id = ? AND status = 'ACTIVE'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? 자리에 memberId 넣기
            pstmt.setInt(1, memberId);

            // SELECT 실행
            rs = pstmt.executeQuery();

            // 결과가 있으면 count 확인
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            System.out.println("활성 구독 확인 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt);
        }

        return false;
    }

    // 구독 추가
    public boolean insertSubscription(int memberId, LocalDate startDate, LocalDate endDate) {

        // 이미 활성 구독이 있으면 중복 구독 불가
        if (ActiveSubscription(memberId)) {
            return false;
        }

        String sql = """
                INSERT INTO subscriptions (member_id, start_date, end_date, status)
                VALUES (?, ?, ?, 'ACTIVE')
                """;

        PreparedStatement pstmt = null;

        try {
            // INSERT 문 준비
            pstmt = con.prepareStatement(sql);

            // 회원 번호 바인딩
            pstmt.setInt(1, memberId);

            // 시작일 바인딩
            pstmt.setDate(2, Date.valueOf(startDate));

            // 종료일 바인딩
            pstmt.setDate(3, Date.valueOf(endDate));

            // INSERT 실행
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("구독 추가 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(pstmt);
        }
    }

    // 회원 번호로 회원이 구독한 강의 목록 조회
    public Subscription findActiveSubscriptionByMemberId(int memberId) {

        String sql = """
                SELECT subscription_id, member_id, start_date, end_date, status
                FROM subscriptions
                WHERE member_id = ? AND status = 'ACTIVE'
                ORDER BY subscription_id DESC
                """;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 실행 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? 자리에 회원 번호 바인딩
            pstmt.setInt(1, memberId);

            // 조회 실행
            rs = pstmt.executeQuery();

            // 조회 결과가 여러 건이어도 rs.next()는 첫 번째 행만 가져옴
            // ORDER BY subscription_id DESC 이므로 가장 최신 구독이 먼저 옴
            if (rs.next()) {
                return new Subscription(
                        rs.getInt("subscription_id"),
                        rs.getInt("member_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            System.out.println("구독 조회 실패: " + e.getMessage());
        } finally {
            // DB 자원 정리
            DBConnection.close(rs, pstmt);
        }

        // 활성 구독이 없으면 null 반환
        return null;

    }

    // 구독 취소
    // 삭제가 아니라 상태를 CANCELED로 변경
    public boolean cancelSubscription(int memberId) {

        String sql = """
                UPDATE subscriptions
                SET status = 'CANCELED'
                WHERE member_id = ? AND status = 'ACTIVE'
                """;

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, memberId);

            // UPDATE 실행
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("구독 취소 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(pstmt);
        }
    }

}
