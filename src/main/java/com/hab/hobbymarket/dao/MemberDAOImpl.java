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
    public boolean save(Member member) {
        String sql = "INSERT INTO members (login_id, password, nickname, name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getLoginId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            pstmt.setString(4, member.getName());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getPhone());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("회원 저장 실패: " + e.getMessage());
            return false;
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

    @Override
    public boolean existsByMemberId(int memberId) {

        // 회원 존재 여부 저장 변수
        boolean exists = false;

        // DB 연결 객체
        Connection conn = null;

        // SQL 실행 객체
        PreparedStatement pstmt = null;

        // SELECT 결과 객체
        ResultSet rs = null;

        // 해당 member_id의 회원이 존재하는지 확인하는 SQL
        String sql = "SELECT 1 FROM members WHERE member_id = ?";

        try {
            // DB 연결
            conn = DBConnection.getConnection();

            // SQL 준비
            pstmt = conn.prepareStatement(sql);

            // 첫 번째 ? 자리에 memberId 넣기
            pstmt.setInt(1, memberId);

            // SELECT 실행
            rs = pstmt.executeQuery();

            // 결과가 한 줄이라도 있으면 존재하는 회원
            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 존재 여부 반환
        return exists;
    }

    @Override
    public int deleteWishlistsByMemberId(int memberId) {

        // 삭제된 행 개수 저장
        int result = 0;

        // DB 연결 객체
        Connection conn = null;

        // SQL 실행 객체
        PreparedStatement pstmt = null;

        // 해당 회원의 관심목록 삭제 SQL
        String sql = "DELETE FROM wishlists WHERE member_id = ?";

        try {
            // DB 연결
            conn = DBConnection.getConnection();

            // SQL 준비
            pstmt = conn.prepareStatement(sql);

            // 첫 번째 ? 자리에 memberId 값 넣기
            pstmt.setInt(1, memberId);

            // DELETE 실행
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 삭제된 행 개수 반환
        return result;
    }

    @Override
    public int deleteEnrollmentsByMemberId(int memberId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM enrollments WHERE member_id = ?";

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

    @Override
    public int deleteSubscriptionsByMemberId(int memberId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM subscriptions WHERE member_id = ? OR instructor_id = ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, memberId);

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteMembersByMemberId(int memberId) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM members WHERE member_id = ?";

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

    @Override
    public boolean updatePassword(String loginId, String name, String newPassword) {

        // 아이디와 이름이 일치하는 회원의 비밀번호를 변경하는 SQL
        String sql = "UPDATE members SET password = ? WHERE login_id = ? AND name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 새 비밀번호
            pstmt.setString(1, newPassword);

            // 본인 확인용 아이디
            pstmt.setString(2, loginId);

            // 본인 확인용 이름
            pstmt.setString(3, name);

            // UPDATE 실행
            int result = pstmt.executeUpdate();

            // 수정된 행이 1개 이상이면 성공
            return result > 0;

        } catch (Exception e) {
            System.out.println("비밀번호 변경 실패: " + e.getMessage());
            return false;
        }
    }

}
