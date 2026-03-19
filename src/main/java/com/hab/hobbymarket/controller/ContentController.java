package com.hab.hobbymarket.controller;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContentController {

    // LEC-001 : 카테고리 조회
    public void showCategory() {

        /* comment.
            SQL 쿼리문 작성 방식
            요구사항 명세서에 따라 카테고리 ID, 카테고리 명, 해당 카테고리의 강의 수를 반환이 목적
            따라서 한 번의 쿼리로 3개의 정보를 가져오기 위해 서브 쿼리 구조를 사용
         */

        /** 전체 흐름
         * 1. SQL 준비
         * 2. DB 연결 (coon)
         * 3. SQL 세팅 (pstmt)
         * 4. SQL 실행 (rs)
         * 5. 결과 한 줄씩 읽으면서 출력
         * 6. 결과가 없다면 "없다" 메세지 출력
         * 7. 에러 나면 에러 메세지 반환
         * 8. 자원은 사용하고 닫아주기
         */

        String sql = "SELECT c.category_id, c.category_name, " +
                "(SELECT COUNT(*) FROM lectures l " +
                "WHERE l.category_id = c.category_id AND l.is_deleted = FALSE) AS lecture_count " +
                "FROM categories c";

        // DB 연결을 담을 변수 선언
        Connection conn = null;

        // SQL 쿼리를 담을 변수 선언
        PreparedStatement pstmt = null;

        // SQL 실행 결과를 담을 변수 선언
        ResultSet rs = null;

        // 이때부터 에러가 발생할 수 있으니, 에러를 줄이기위해 사용
        try {
            // DB에 연결을 성공하면 conn에 연결 객체가 들어옵니다.
            conn = DBConnection.getConnection();

            // SQ을 실행할 준비하라고 요청하는 단계
            pstmt = conn.prepareStatement(sql);

            // SQL 을 실제로 실행하며, 결과를 ResultSet에 담아서 리턴
            rs = pstmt.executeQuery();

            boolean hasData = false;

            System.out.println("\n=== 카테고리 목록 ===");

            // ResultSet에서 한 줄씩 읽기 위해 반복문(while 문 사용)
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");
                int count = rs.getInt("lecture_count");

                System.out.printf("%d. %s (%d개 강의)\n", id, name, count);
            }

            // 만약 데이터가 없으면 등록된 카테고리 없다고 안내하기
            if (!hasData) {
                System.out.println("등록된 카테고리가 없습니다.");
            }

          // try 구문에서 에러가 발생하면 e.getMessage()로 에러 원인을 출력해준다.
        } catch (Exception e) {
            System.out.println("카테고리 조회 실패: " + e.getMessage());

            // finally를 통해서 사용한 자원 닫아주기
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }


    }

}
