package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.global.config.DBConnection;
import com.hab.hobbymarket.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    // 더미 데이터 저장소 (DB 대신 메모리에서 관리)
    private static List<Member> memberList = new ArrayList<>();

    // 테스트용 더미 데이터
    static {
        memberList.add(new Member("testUser1", "Test1234!", "테스터", "홍길동"));
        memberList.add(new Member("admin123", "Admin1234!", "관리자", "김관리"));
    }

    // 아이디 중복 체크
    @Override
    public boolean existsByLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return true;
            }
        }
        return false;
    }

    // 회원 저장
    @Override
    public void save(Member member) {
        memberList.add(member);
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

    // 회원 탈퇴 (INACTIVE 처리)
    @Override
    public int deactivateMember(int memberId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE members SET status = 'INACTIVE' WHERE member_id = ?";


        // 현재 아직 로그인이 완성이 안됐기 때문에 데이터 말소 기능은 안넣은 상태입니다. 로그인 기능이 구현 됐을 때 추가하기
        //String sql = "UPDATE members SET status = 'INACTIVE' WHERE member_id = ? AND status = 'ACTIVE'";

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