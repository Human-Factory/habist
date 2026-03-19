package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {


    /** [DAO 작성 흐름]
     * 1. SQL 생성
     * 2. DB 연결
     * 3. ?가 있다면 SQL 파라미터 넣기
     * 4. 실행
     * 5. 결과 담기
     * 6. 자원 닫기
     * 7. 결과 리턴
     */


    // 카테고리 목록 조회
    public List<String[]> getCategories() {
        List<String[]> list = new ArrayList<>();

        // SQL : 카테고리 정보와 함께, "서브쿼리"를 사용해 해당 카테고리를 활성 강의 수 계산하기
        String sql = "SELECT c.category_id, c.category_name, " +
                "(SELECT COUNT(*) FROM lectures l " +
                "WHERE l.category_id = c.category_id AND l.is_deleted = FALSE) AS lecture_count " +
                "FROM categories c";

        // DB 3형제
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Error 발생으로 인해 코드가 죽는 것을 방지
        try {
            conn = DBConnection.getConnection(); // DB 연결하기
            pstmt = conn.prepareStatement(sql); // 쿼리 준비
            rs = pstmt.executeQuery(); // 쿼리 실행 및 결과를 수신하는 역할

            // 결과 한 줄 씩 읽기
            while (rs.next()) {
                // 조회된 데이터를 String 배열을 통해 묶기
                String[] row = {
                        String.valueOf(rs.getInt("category_id")),
                        rs.getString("category_name"),
                        String.valueOf(rs.getInt("lecture_count"))
                };
                list.add(row); // 위의 내용을 리스트에 추가
            }
            // Error가 발생 시 발생 원인을 Error 로 반환
        } catch (Exception e) {
            System.out.println("카테고리 조회 실패: " + e.getMessage());
            // 다 사용한 자원 정리 [ 메모리 누수를 위해 ] & try with resource를 통해서하면 finally를 사용하지 않아도 된다고 한다.
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        // list 에 값을 반환
        return list;
    }

    // 카테고리 존재 여부 확인
    public boolean categoryExists(int categoryId) {

        // SQL : 입력받은 category_id와 일치하는 데이터의 개수를 확인해주는 쿼리문
        String sql = "SELECT COUNT(*) FROM categories WHERE category_id = ?";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId); // 첫 번째 '?' 자리에 전달받은 ID 삽입
            rs = pstmt.executeQuery();

            rs.next(); // 결과의 첫 번째 행으로 이동
            return rs.getInt(1) > 0; // 처음 넣는 정보기 때문에 무조건 성립
        } catch (Exception e) {
            System.out.println("카테고리 확인 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
    }

    // 카테고리별 강의 목록 조회
    public List<String[]> getLecturesByCategory(int categoryId) {
        List<String[]> list = new ArrayList<>();

        // SQL : lectures 테이블과 members 테이블을 JOIN해서 강사 이름까지 함께 조회
        String sql = "SELECT l.lecture_id, l.title, m.nickname AS instructor_name, " +
                "l.view_count, l.like_count " +
                "FROM lectures l " +
                "JOIN members m ON l.instructor_id = m.member_id " +
                "WHERE l.category_id = ? AND l.is_deleted = FALSE";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 시스템이 죽는 것을 방지하기 위해 try-catch 구문 작성
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 강의 ID, 제목, 강사 이름, 조회수, 좋아요 수를 배열에 담는 역할
                String[] row = {
                        String.valueOf(rs.getInt("lecture_id")),
                        rs.getString("title"),
                        rs.getString("instructor_name"),
                        String.valueOf(rs.getInt("view_count")),
                        String.valueOf(rs.getInt("like_count"))
                };
                list.add(row); // 리스트 받은 내용을 추가
            }
        } catch (Exception e) {
            System.out.println("강의 목록 조회 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return list;
    }
}