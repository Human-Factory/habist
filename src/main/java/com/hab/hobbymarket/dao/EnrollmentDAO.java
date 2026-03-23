package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    // DB 연결 객체
    private final Connection con;

    // 생성자에서 Connection 주입
    public EnrollmentDAO(Connection con) {
        this.con = con;
    }

    // --------------------------------------------------
    // 1. 로그인한 회원의 학습 목록 조회
    // --------------------------------------------------
    public List<Enrollment> findByMemberId(int memberId) {

        // 결과를 담을 리스트
        List<Enrollment> list = new ArrayList<>();

        // SQL (강의 제목도 같이 가져오기 위해 JOIN 사용)
        String sql = """
                SELECT e.enrollment_id,
                       e.member_id,
                       e.lecture_id,
                       l.title,
                       e.progress_percent,
                       e.last_position,
                       e.enrolled_at
                FROM enrollments e
                JOIN lectures l ON e.lecture_id = l.lecture_id
                WHERE e.member_id = ?
                ORDER BY e.enrollment_id DESC
                """;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 준비
            pstmt = con.prepareStatement(sql);

            // ? 바인딩
            pstmt.setInt(1, memberId);

            // 실행
            rs = pstmt.executeQuery();

            // 결과 반복 처리
            while (rs.next()) {

                // DB 결과 → 객체로 변환
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("member_id"),
                        rs.getInt("lecture_id"),
                        rs.getString("title"),
                        rs.getInt("progress_percent"),
                        rs.getString("last_position"),
                        rs.getTimestamp("enrolled_at")
                );

                // 리스트에 추가
                list.add(enrollment);
            }

        } catch (Exception e) {
            System.out.println("학습 조회 실패: " + e.getMessage());
        } finally {
            // 자원 해제
            DBConnection.close(rs, pstmt);
        }

        return list;
    }

    // --------------------------------------------------
    // 2. 이어보기용 단건 조회
    // --------------------------------------------------
    public Enrollment findById(int enrollmentId, int memberId) {

        String sql = """
                SELECT e.enrollment_id,
                       e.member_id,
                       e.lecture_id,
                       l.title,
                       e.progress_percent,
                       e.last_position,
                       e.enrolled_at
                FROM enrollments e
                JOIN lectures l ON e.lecture_id = l.lecture_id
                WHERE e.enrollment_id = ? AND e.member_id = ?
                """;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, enrollmentId);
            pstmt.setInt(2, memberId);

            rs = pstmt.executeQuery();

            // 결과 1건 반환
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("member_id"),
                        rs.getInt("lecture_id"),
                        rs.getString("title"),
                        rs.getInt("progress_percent"),
                        rs.getString("last_position"),
                        rs.getTimestamp("enrolled_at")
                );
            }

        } catch (Exception e) {
            System.out.println("이어보기 조회 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt);
        }

        return null;
    }

    // --------------------------------------------------
    // 3. 진척도 업데이트
    // --------------------------------------------------
    public boolean updateProgress(int enrollmentId, int memberId,
                                  int progressPercent, String lastPosition) {

        String sql = """
                UPDATE enrollments
                SET progress_percent = ?, last_position = ?
                WHERE enrollment_id = ? AND member_id = ?
                """;

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, progressPercent);
            pstmt.setString(2, lastPosition);
            pstmt.setInt(3, enrollmentId);
            pstmt.setInt(4, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("진척도 업데이트 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(pstmt);
        }
    }
}
