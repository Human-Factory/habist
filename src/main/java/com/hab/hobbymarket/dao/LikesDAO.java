package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <역할>
 *     lecture_likes 테이블 접근 담당 (좋아요 등록/취소/중복확인)
 * <연관테이블>
 *     lecture_likes, lectures
 *
 * <처리 원칙>
 *     1. 좋아요 등록 전 already_likes() 로 중복 여부 반드시 확인
 *     2. addLike() : lecture_likes INSERT -> lectures.like_count +1 순서로 실행
 *     3. removeLike() : lecture_likes DELETE -> like_count -1 순서로 실행
 *     하지만! like_count > 0 조건으로 0 이하 방지를 DB 레벨에서 처리
 */

public class LikesDAO {

    // 1. 좋아요 중복 여부 확인
    /* comment.
        <역할>
        해당 회원이 해당 강의에 이미 좋아요를 눌렀는지 확인하는 목적
        <용도>
        Controller 에서 addLike() 호출 전 사전 체크용으로 사용
     */
    public boolean alreadyLiked(int memberId, int lectureId) {
        // SQL 쿼리문 작성 (member_id 와 lecture_id 동시 조건)
        String sql = "SELECT COUNT(*) FROM lecture_likes " +
                "WHERE member_id = ? AND lecture_id = ?";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //
        try {
            conn = DBConnection.getConnection(); // DB 연결
            pstmt = conn.prepareStatement(sql); // 쿼리 준비 (위에서 작성한 쿼리)
            pstmt.setInt(1, memberId); // 첫 번째 ? 에 memberId 삽입
            pstmt.setInt(2, lectureId); // 두 번째 ? 에 lectureId 삽입
            rs = pstmt.executeQuery(); // 쿼리 실행

            /* comment.
                기존과 다른 try - catch 구문을 작성했다. 이유는 다음과 같다.
                SQL 에서 SELECT COUNT(*)이다. COUNT는 무조건 숫자 1개만 반환한다.
                결과가 항상 딱 1행 1열이다. 따라서 while 문을 쓸 필요없이 rs.next() 한 번만 사용해
                rs.getInt(1) 로 첫 번째 컬럼 값을 꺼내오는 것이다.
             */

            rs.next();  // 결과가 딱 1줄로 나오게 되는데, while 없이 한 번만 이동
            return rs.getInt(1) > 0; // 그 1줄의 첫 번째 컬럼 값을 꺼내서 판단
            // Error 발생 시 원인 분석
        } catch (Exception e) {
            System.out.println("좋아요 중복 확인 실패: " + e.getMessage());
            return false;
            // 사용한 자원 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
    }

    // 2. 좋아요 등록 + lectures 테이블 like_count +1

    /* comment.
        <역할>
        좋아요 등록
        <흐름도>
        1. lecture_likes 테이블에 INSERT (member_id, lecture_id)
        2. lectures 테이블 like_count + 1
        <핵심 영역>
        pstmt.close() 후 두 번째 쿼리 실행하며 같은 con 재사용한다.
     */

    public void addLike(int memberId, int lectureId) {

        // SQL 쿼리문이 2개인 이유 : 좋아요를 눌렀을 경우 2가지 일을 동시에 일어나야한다.
        // lecture_likes 테이블에 "누가 어떤 강의를 좋아요 눌렀다" 기록 저장
        // lectures 테이블 like_count 숫자를 + 1
        String insertSql = "INSERT INTO lecture_likes (member_id, lecture_id) VALUES (?, ?)";
        String updateSql = "UPDATE lectures SET like_count = like_count + 1 WHERE lecture_id = ?";

        // 마찬가지로 UPDATE 쿼리문을 작성했기 때문에 result는 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection(); // DB 연결

            // 첫 번째 쿼리문 실행 (INSERT)
            pstmt = conn.prepareStatement(insertSql); // insertSQL wnsql
            pstmt.setInt(1, memberId); // 첫 번쨰 ? : memberId
            pstmt.setInt(2, lectureId); // 두 번째 ? : lectureId
            pstmt.executeUpdate(); // INSERT 실행
            pstmt.close();

            // 두 번쨰 쿼리문 실행 (UPDATE)
            pstmt = conn.prepareStatement(updateSql); // UPDATESQL 새로 준비
            pstmt.setInt(1, lectureId); // 첫 번쨰 ? : lectureId
            pstmt.executeUpdate(); // update 실행
            // Error 발생 시 원인 출력
        } catch (Exception e) {
            System.out.println("좋아요 등록 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 3. 좋아요 취소 + lectures 테이블 like_count -1 (0 이하 방지)

    /* comment.
        <역할>
        좋아요 취소
        <흐름도>
        1. lecture_likes 테이블에서 DELETE (member_id, lecture_id 조건)
        2. lectures 테이블 like_count -1 (하지만, like_count > 0 일 때만 실행)
        <포인트>
        like_count > 0 조건을 SQL 안에 걸어서 음수 방지!!!
     */

    public void removeLike(int memberId, int lectureId) {

        // SQL 쿼리문 : lecture_likes 테이블에서 "이 사람이 이 강의에 누른 좋아요 기록" 삭제
        // lectures 테이블의 like_count -1 / 하지만 like_count > 0 조건이 꼭 있어야함
        String deleteSql = "DELETE FROM lecture_likes WHERE member_id = ? AND lecture_id = ?";
        String updateSql = "UPDATE lectures SET like_count = like_count - 1 " +
                "WHERE lecture_id = ? AND like_count > 0"; // 이 부분이 2번째 쿼리에서 핵심포인트!!!

        // 마찬가지로 UPDATE 쿼리를 사용했기 때문에 result가 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection(); // DB 연결

            pstmt = conn.prepareStatement(deleteSql); // deleteSQL 준비
            pstmt.setInt(1, memberId); // 첫 번째 ? -> memberId
            pstmt.setInt(2, lectureId); // 두 번째 ? -> lectureId
            pstmt.executeUpdate(); // DELETE 실행
            pstmt.close(); // 첫 번째 쿼리 실행 종료

            pstmt = conn.prepareStatement(updateSql); // updateSQL 새로 준비
            pstmt.setInt(1, lectureId); // 첫 번째 ? lectureId
            pstmt.executeUpdate(); // UPDATE 실행

            // Error 원인 파악을 위해 사용
        } catch (Exception e) {
            System.out.println("좋아요 취소 실패: " + e.getMessage());
            // 사용한 리소스 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }
}