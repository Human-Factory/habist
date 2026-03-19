package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member findByLoginId(String loginId) {

        // 로그인 시 아이디로 회원 1명을 조회하는 SQL
        String sql = """
                SELECT member_id, login_id, password, nickname, name, email, phone, role, status, created_at, updated_at
                FROM members
                WHERE login_id = ?
                """;

        // DB 연결 → SQL 실행 → 결과 처리
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    return new Member(
                            rs.getLong("member_id"),
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nickname"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at") == null ? null :
                                    rs.getTimestamp("updated_at").toLocalDateTime()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("회원 조회 실패: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean existsByLoginId(String loginId) {
        // 회원가입 파트에서 사용할 예정 (지금은 미구현)
        return false;
    }

    @Override
    public void save(Member member) {
        // 회원가입 파트에서 사용할 예정 (지금은 미구현)
    }
}