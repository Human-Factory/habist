package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InquiryDAO {

    // 문의 작성
    public boolean save(Inquiry inquiry) {
        String sql = "INSERT INTO inquiries (member_id, title, content) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, inquiry.getMemberId());
            pstmt.setString(2, inquiry.getTitle());
            pstmt.setString(3, inquiry.getContent());

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("문의 작성 실패: " + e.getMessage());
            return false;
        }
    }

    // 내 문의 목록 조회
    public List<Inquiry> findByMemberId(Long memberId) {
        List<Inquiry> list = new ArrayList<>();
        String sql = """
                SELECT inquiry_id, member_id, title, content, answer,
                       is_answered, created_at, answered_at
                FROM inquiries
                WHERE member_id = ?
                ORDER BY created_at DESC
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Inquiry(
                            rs.getLong("inquiry_id"),
                            rs.getLong("member_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("answer"),
                            rs.getBoolean("is_answered"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("answered_at") == null ? null :
                                    rs.getTimestamp("answered_at").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("문의 목록 조회 실패: " + e.getMessage());
        }
        return list;
    }

    // 문의 수정 (본인만)
    public boolean update(Long inquiryId, Long memberId, String title, String content) {
        String sql = "UPDATE inquiries SET title = ?, content = ? WHERE inquiry_id = ? AND member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setLong(3, inquiryId);
            pstmt.setLong(4, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("문의 수정 실패: " + e.getMessage());
            return false;
        }
    }

    // 문의 삭제 (본인만)
    public boolean delete(Long inquiryId, Long memberId) {
        String sql = "DELETE FROM inquiries WHERE inquiry_id = ? AND member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, inquiryId);
            pstmt.setLong(2, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("문의 삭제 실패: " + e.getMessage());
            return false;
        }
    }
}
