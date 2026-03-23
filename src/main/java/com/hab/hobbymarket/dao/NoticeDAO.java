package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {

    /**
     * <역할>
     *     notices 테이블 접근 담당 (공지사항 등록/목록/상세/수정/삭제)
     *
     * <설계 결정 사항>
     *     1. author_id 는 FK 없는 일반 INT - 팀 합의로 members JOIN 없이 직접 처리하는 방식
     *     2. content 는 text 타입 - varchar 보다 큰 데이터 저장 가능 ( 공지사항 특성 )
     *     3. 조회수 증가 (increaseViewCount) 는 Controller 에서 상세 조회 전에 먼저 호출
     *     4. updated_at 는 DB 가 자동 갱신 (on update CURRENT_TIMESTAMP)
     */

    /* comment.
        <역할>
        공지사항 등록
        authorId - 작성자 ID / title - 제목 / content - 내용
     */
    public void addNotice(int authorId, String title, String content) {
        String sql = "INSERT INTO notices (author_id, title, content) VALUES (?, ?, ?)";

        // ResultSet이 없는 이유는 INSERT/UPDATE/DELETE 는 결과가 없고 처리만 하기 때문에 불필요하다는
        // 사실을 알게 되었다. 따라서 ResultSet이 필요없다.
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, authorId); // 첫 번째 ? -> author_id
            pstmt.setString(2, title); // 두 번째 ? -> title
            pstmt.setString(3, content); // 세 번째 ? -> content
            pstmt.executeUpdate();
            // Error 가 발생했을 때 원인을 출력
        } catch (Exception e) {
            System.out.println("공지사항 등록 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 2. 공지사항 목록 조회

    /* comment.
        <역할>
        공지사항 전체 목록 조회
        <반환 배열 구조>
            row[0] = notice_id
            row[1] = author_id
            row[2] = title
            row[3] = view_count
            row[4] = created_at
     */

    // 새로운 리스트 제작 및 선언
    public List<String[]> getNotices() {
        List<String[]> list = new ArrayList<>();

        // SQL 쿼리문 (공지사항에 들어있는 모든 테이블 조회 및 정렬)
        String sql = "SELECT notice_id, author_id, title, view_count, created_at " +
                "FROM notices " +
                "ORDER BY created_at DESC";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // try-catch 구문으로 예외 처리 실행
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // while 반복문을 통해서 다음 내용이 있을 때 까지 추가
            while (rs.next()) {
                String[] row = {
                        String.valueOf(rs.getInt("notice_id")),
                        String.valueOf(rs.getInt("author_id")),
                        rs.getString("title"),
                        String.valueOf(rs.getInt("view_count")),
                        rs.getString("created_at")
                };
                // list 에 Stirng[] row 의 내용을 추가하기
                list.add(row);
            }
            // Error 가 발생했을 때 오류를 추력
        } catch (Exception e) {
            System.out.println("공지사항 목록 조회 실패: " + e.getMessage());
            // 사용한 리소스 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        // 리스트에 반환
        return list;
    }

    // 3. 공지사항 상세 조회 + 조회수 증가

    /* comment.
        <역할>
        공지사항 상세 조회
        <반환 배열 구조>
            row[0] = notice_id
            row[1] = author_id
            row[2] = title
            row[3] = content  ← 목록과 달리 content 포함
            row[4] = view_count
            row[5] = created_at
     */

    public String[] getNoticeDetail(int noticeId) {
        // SQL 쿼리문 ( 반환하려고하는 배열 구조를 찾고 noticeId에 넣기)
        String sql = "SELECT notice_id, author_id, title, content, view_count, created_at " +
                "FROM notices WHERE notice_id = ?";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // try-catch 구문 작성
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeId);
            rs = pstmt.executeQuery();

            // if 조건문을 통해서 다음 내용이 없으면 계속해서 String[] 내열 안에 넣기
            if (rs.next()) {
                return new String[]{
                        String.valueOf(rs.getInt("notice_id")),
                        String.valueOf(rs.getInt("author_id")),
                        rs.getString("title"),
                        rs.getString("content"),
                        String.valueOf(rs.getInt("view_count")),
                        rs.getString("created_at")
                };
            }
            // Error 발생 시 문제를 출력
        } catch (Exception e) {
            System.out.println("공지사항 상세 조회 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return null;
    }

    // 4. 공지사항 조회수 증가

    /* comment.
        <역할>
        누군가가 봤을 때 공지사항 조회수 + 1로 올리기
        <흐름>
        Controller 에서 getNoticeDetail() 호출 전에 먼저 실행
        ContentDAO.increaseViewCount() 와 완전히 동일한 패턴
     */

    public void increaseViewCount(int noticeId) {
        // SQL 쿼리문( WHERE 문을 통해서 +1 씩 카운트 해주는 역할 진행
        String sql = "UPDATE notices SET view_count = view_count + 1 WHERE notice_id = ?";

        // UPDATE 기능이기 때문에 result가 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        // try - catch 구문을 사용해서
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeId); // SQL 쿼리문의 ?
            pstmt.executeUpdate();
            // Error 발생 시 문제 출력
        } catch (Exception e) {
            System.out.println("조회수 증가 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 5. 공지사항 수정

    /* comment.
        <역할>
        공지사항 조회수 + 1 역할
        <흐름도>
        SQL 순서 - title (1번) -> content(2번) + notice_id(3번) 순서 맞춰야한다.
        updated_at 은 DB 자동 갱신
     */

    public void updateNotice(int noticeId, String newTitle, String newContent) {
        // SQL 쿼리문 (? 을 통해서 쿼리문 작성)
        String sql = "UPDATE notices SET title = ?, content = ? WHERE notice_id = ?";

        // UPDATE 쿼리기 때문에 Result가 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newTitle); // 첫 번째 ? -> title
            pstmt.setString(2, newContent); // 두 번째 ? -> content
            pstmt.setInt(3, noticeId); // 세 번째 ? -> notice_id
            pstmt.executeUpdate();
            // Error 발생 시 오류 출력
        } catch (Exception e) {
            System.out.println("공지사항 수정 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // 6. 공지사항 삭제

    /* comment.
        <역할>
        공지사항 삭제
        <포인트>
        notice_id 하나로 단순 DELETE - deleteComment() 와 동일한 패턴
     */

    public void deleteNotice(int noticeId) {
        // SQL 쿼리문 (삭제하는 용도)
        String sql = "DELETE FROM notices WHERE notice_id = ?";

        // DELETE 를 사용하기 때문에 ResultSet이 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeId); // 첫 번째 > -> notice_id
            pstmt.executeUpdate();
            // Error 발생 시 오류 출력
        } catch (Exception e) {
            System.out.println("공지사항 삭제 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }
}
