package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * <역할>
 *     lecture_comments 테이블 접근 담당 (댓글 작성/조회/수정/삭제)
 * <처리 원칙>
 *     1. 수정 & 삭제 전 isCommentOwner() 로 본인 여부 반드시 확인
 *     2. content 는 varchar(500) 제한 - Controller 에서 사전 검증
 *     3. updated_at 은 DB 가 자동 갱신 (on update CURRENT_TIMESTAMP)
 */

public class CommentsDAO {

    // 1. 댓글 작성

    /* comment.
        <역할>
        댓글 작성
        <흐름>
        lecture_comments 테이블 INSERT (member_id, lecture_id, content)
     */

    public void addComment(int memberId, int lectureId, String content) {
        // SQL 쿼리문 (멤버 번호, 강의 번호, 콘텐츠 ? 로 표시)
        String sql = "INSERT INTO lecture_comments (member_id, lecture_id, content) " +
                "VALUES (?, ?, ?)";

        // result 가 없어서 2개만 사용
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId); // 첫 번째 ? -> 작성자 ID
            pstmt.setInt(2, lectureId); // 두 번째 ? -> 강의 ID
            pstmt.setString(3, content); // 세 번째 ? -> 댓글 내용 (String 형태라 setString으로)
            pstmt.executeUpdate(); // select이 아닌 Insert 구문
            // Error 발생 시 원인 출력
        } catch (Exception e) {
            System.out.println("댓글 작성 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 2. 댓글 목록 조회 (강의 ID 기준)

    /* comment.
        <역할>
        특정 강의의 댓글 목록 조회
        <반환 배열 구조>
         row[0] = comment_id
        row[1] = nickname (members 테이블 JOIN)
        row[2] = content
        row[3] = created_at
        row[4] = member_id (본인 여부 판단용)
     */

    public List<String[]> getCommentsByLecture(int lectureId) {
        List<String[]> list = new ArrayList<>();

        // SQL 구문 (lecture_comments 와 members를 조인 / ORDER BY 를 통해 오래된 댓글부터 위에서 표시
        String sql = "SELECT c.comment_id, m.nickname, c.content, c.created_at, c.member_id " +
                "FROM lecture_comments c " +
                "JOIN members m ON c.member_id = m.member_id " +
                "WHERE c.lecture_id = ? " +
                "ORDER BY c.created_at ASC";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lectureId); // ? 강의 ID
            rs = pstmt.executeQuery(); // SELECT -> executeQuery 사용

            while (rs.next()) {
                String[] row = {
                        String.valueOf(rs.getInt("comment_id")), //row[0]
                        rs.getString("nickname"), // row[1]
                        rs.getString("content"),  //row[2]
                        rs.getString("created_at"), // row[3]
                        String.valueOf(rs.getInt("member_id")) // row[4] 본이 여부 판단용
                };
                list.add(row);
            }
            // Error 발생 시 원이 파악
        } catch (Exception e) {
            System.out.println("댓글 조회 실패: " + e.getMessage());
            // 사용한 리소스 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return list;
    }

    // 3. 댓글 작성자 확인 (본인 여부 검증)

    /* comment.
        <역할>
        댓글 작성자 본인 여부 판단 확인용
        <흐름도>
        true = 본인 / false = 타인
     */

    public boolean isCommentOwner(int commentId, int memberId) {

        // SQL 구문(댓글 ID + 회원 ID 두 조건이 동시에 맞는 행이 있는지 확인 / 둘 다 TRUE여야 본인확인)
        String sql = "SELECT COUNT(*) FROM lecture_comments " +
                "WHERE comment_id = ? AND member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, commentId); // 첫 번째 ? -> 댓글 ID
            pstmt.setInt(2, memberId); // 두 번째 ? -> 회원 ID
            rs = pstmt.executeQuery();

            rs.next(); // COUNT 결과는 항상 1행 -> while 아닌 rs.next() 한 번
            return rs.getInt(1) > 0; // 1 이상이면 본인 -> true / 0이면 타인 -> false
            // Error 발생 시 원인 제공
        } catch (Exception e) {
            System.out.println("댓글 작성자 확인 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
    }

    // 4. 댓글 수정 (본인만)

    /* comment.
        <역할>
        댓글 내용 수정
        <흐름도>
        content 만 UPDATE / updated_at 은 DB 가 자동 갱신
     */

    public void updateComment(int commentId, String newContent) {
        // SQL 구문(content만 변경. 따라서 update_at 은 DDL 에서 on update CURRENT_TIMESTAMP 로 설정
        // 즉, DB 가 자동으로 현재 시각으로 갱신
        String sql = "UPDATE lecture_comments SET content = ? " +
                "WHERE comment_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newContent); // 첫 번째 ? -> 새 댓글 내용
            pstmt.setInt(2, commentId); // 두 번째 ? -> 수정할 댓글 ID
            pstmt.executeUpdate(); // UPDATE -> executeUpdate 사용
            // Error 발생 시 원인을 파악하기 위해서 사용
        } catch (Exception e) {
            System.out.println("댓글 수정 실패: " + e.getMessage());
            // 사용한 리소스 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 5. 댓글 삭제 (본인만)

    /* comment.
        <역할>
        댓글 삭제
        <흐름도>
        isCommentOwner() 확인 후 Controller 에서 호출
     */

    public void deleteComment(int commentId) {
        // SQL 쿼리문 (댓글 ID 하나로 해당 행 전체 삭제)
        String sql = "DELETE FROM lecture_comments WHERE comment_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, commentId); // ? -> 삭제할 댓글 ID
            pstmt.executeUpdate(); // DELETE -> executeUpdate 사용
            // Error 발생 시 원인을 출력
        } catch (Exception e) {
            System.out.println("댓글 삭제 실패: " + e.getMessage());
            // 사용한 자원 리소스 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }
}