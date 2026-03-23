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

    // 1. 로그인한 회원의 학습 목록 조회
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

    // 2. 이어보기용 단건 조회
    public Enrollment findById(int enrollmentId, int memberId) {

        // SQL 쿼리 작성
        // - enrollments 테이블에서 수강 정보 가져옴
        // - lectures 테이블과 JOIN 해서 강의 제목(title)도 같이 가져옴
        // - 특정 수강(enrollment_id) + 현재 로그인한 회원(member_id) 조건
        String sql = """
            
                SELECT e.enrollment_id,      -- 수강 고유번호
                   e.member_id,          -- 회원 번호
                   e.lecture_id,         -- 강의 번호
                   l.title,              -- 강의 제목 (JOIN)
                   e.progress_percent,   -- 진척도 (%)
                   e.last_position,      -- 마지막 학습 위치
                   e.enrolled_at         -- 수강 시작 시간
            FROM enrollments e
            JOIN lectures l ON e.lecture_id = l.lecture_id
            WHERE e.enrollment_id = ? AND e.member_id = ?
            """;

        // SQL 실행을 위한 객체
        PreparedStatement pstmt = null;

        // 조회 결과를 담는 객체
        ResultSet rs = null;

        try {
            // SQL을 DB에 전달하기 위해 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? → enrollmentId (수강번호)
            pstmt.setInt(1, enrollmentId);

            // 두 번째 ? → memberId (로그인한 사용자)
            pstmt.setInt(2, memberId);

            // SELECT 쿼리 실행 → 결과를 rs(ResultSet)에 담음
            rs = pstmt.executeQuery();

            // rs.next() → 결과가 있으면 true (첫 번째 행으로 이동)
            if (rs.next()) {

                // DB 결과를 Enrollment 객체로 변환해서 반환
                return new Enrollment(
                        rs.getInt("enrollment_id"),     // 수강번호
                        rs.getInt("member_id"),         // 회원번호
                        rs.getInt("lecture_id"),        // 강의번호
                        rs.getString("title"),          // 강의제목
                        rs.getInt("progress_percent"),  // 진척도
                        rs.getString("last_position"),  // 마지막 위치
                        rs.getTimestamp("enrolled_at")  // 수강시간
                );
            }

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 출력
            System.out.println("이어보기 조회 실패: " + e.getMessage());
        } finally {
            // DB 자원 정리 (메모리 누수 방지)
            DBConnection.close(rs, pstmt);
        }

        // 결과가 없으면 null 반환
        return null;
    }

    // 3. 진척도 업데이트
    public boolean updateProgress(int enrollmentId, int memberId,
                                  int progressPercent, String lastPosition) {

        // UPDATE 쿼리 작성
        // - 진척도(progress_percent)와 마지막 위치(last_position) 수정
        // - 본인의 수강(enrollment_id + member_id)만 수정하도록 조건 설정
        String sql =
                """
            UPDATE enrollments
            SET progress_percent = ?
                ,   -- 새 진척도
                            last_position = ?       -- 새 위치
            WHERE
                enrollment_id = ? AND member_id = ?
            """;

        // SQL 실행 객체
        PreparedStatement pstmt = null;

        try {
            // SQL 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? → 진척도 (%)
            pstmt.setInt(1, progressPercent);

            // 두 번째 ? → 마지막 위치 (문자열)
            pstmt.setString(2, lastPosition);

            // 세 번째 ? → 수강번호
            pstmt.setInt(3, enrollmentId);

            // 네 번째 ? → 회원번호 (본인 확인)
            pstmt.setInt(4, memberId);

            // UPDATE 실행
            // executeUpdate() → 영향을 받은 행 수 반환
            // 1 이상이면 성공 → true
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 출력
            System.out.println("진척도 업데이트 실패: " + e.getMessage());

            // 실패 시 false 반환
            return false;

        } finally {
            // DB 자원 정리
            DBConnection.close(pstmt);
        }
    }
}
