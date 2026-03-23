package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InquiryDAO {

    // 1. import 필요한 것들
    // DBConnection, Inquiry, Connection, PreparedStatement, ResultSet, List, ArrayList

    // 2. 문의 목록 조회 메소드
    public List<Inquiry> findByMemberId(Long memberId) {

        // 결과 담을 리스트
        List<Inquiry> list = new ArrayList<>();

        // SQL 작성
        String sql = """
                SELECT inquiry_id, member_id, title, content,
                       answer, is_answered, created_at, answered_at
                FROM inquiries
                WHERE member_id = ?
                ORDER BY created_at DESC
                """;

        // try-with-resources로 Connection, PreparedStatement 자동 닫기
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // ? 자리에 memberId 넣기
            pstmt.setLong(1, memberId);

            // SELECT 실행
            try (ResultSet rs = pstmt.executeQuery()) {

                // 결과 한 줄씩 읽어서 Inquiry 객체로 변환
                while (rs.next()) {
                    list.add(new Inquiry(
                            rs.getLong("inquiry_id"),
                            rs.getLong("member_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("answer"),
                            rs.getBoolean("is_answered"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("answered_at")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("문의 목록 조회 실패: " + e.getMessage());
        }

        return list; // 리스트 반환
    }
}
