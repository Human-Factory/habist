package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAOImpl implements MemberDAO {

    // 아이디 중복 체크
    @Override
    public boolean existsByLoginId(String loginId) {
        String sql = "SELECT COUNT(*) FROM members WHERE login_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            System.out.println("아이디 중복 체크 실패: " + e.getMessage());
        }
        return false;
    }

    // 회원 저장
    @Override
    public void save(Member member) {
        String sql = "INSERT INTO members (login_id, password, nickname, name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            pstmt.setString(4, member.getName());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getPhone());

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("회원 저장 실패: " + e.getMessage());
        }
    }

    // 로그인용 회원 조회
    @Override
    public Member findByLoginId(String loginId) {
        String sql = """
                SELECT member_id, login_id, password, nickname, name, email, phone, role, status, created_at, updated_at
                FROM members
                WHERE login_id = ?
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nickname"),
                            rs.getString("name")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("회원 조회 실패: " + e.getMessage());
        }
        return null;
    }


    // =====================================================================================


    // 회원 탈퇴 (INACTIVE 처리)
    @Override
    public int deactivateMember(int memberId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE members SET status = 'INACTIVE' WHERE member_id = ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
