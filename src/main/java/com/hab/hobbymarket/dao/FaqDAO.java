package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * <역할>
 *     DB 접근 전담을 담당한다. Scanner 로 데이터를 받고, 판단 없고, 출력이 없다.
 */

public class FaqDAO {

    // FAQ의 내용을 추가한다. 질문, 답변내용, 카테고리를 추가한다.
    public void addFaq(String question, String answer, String category) {
        // SQL 쿼리문 (질문,답변,카테고리)
        String sql = "INSERT INTO faqs (question, answer, category) VALUES (?, ?, ?)";

        // INSERT 구문이기 때문에 ResultSet 은 필요없음
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question); // 첫 번째 ? -> question
            pstmt.setString(2, answer); // 두 번째 ? -> answer
            pstmt.setString(3, category); // 세 번째 ? -> categroy
            pstmt.executeUpdate();
            // Error 발생 시 문제 출력
        } catch (Exception e) {
            System.out.println("FAQ 등록 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // FAQ의 내역을 조회한다. FAQ 번호, 질문, 카테고리, 생성일로 조회를 하며 생성일을 기준으로 정렬한다.
    public List<String[]> getFaqs() {
        // 리스트 생성 및 선언
        List<String[]> list = new ArrayList<>();

        // SQL 쿼리문 ( faq_id, 질문, 카테고리, 생성일 / 생성일 기준으로 정렬하기)
        String sql = "SELECT faq_id, question, category, created_at " +
                "FROM faqs " +
                "ORDER BY created_at DESC";

        // SELECT 를 사용했기 때문에 ResultSet이 필요해서 DB 3총사 재등장
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // while 반복문을 통해 추가할 내용이 있을동안 계속 넘기기
            while (rs.next()) {
                String[] row = {
                        String.valueOf(rs.getInt("faq_id")),
                        rs.getString("question"),
                        rs.getString("category"),
                        rs.getString("created_at")
                };
                // list에 받은 내용을 추가
                list.add(row);
            }
            // Error 가 발생하면 내용 출력
        } catch (Exception e) {
            System.out.println("FAQ 목록 조회 실패: " + e.getMessage());
            // 사용한 리소스 자원 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return list;
    }

    // FAQ 상세조회하기
    public String[] getFaqDetail(int faqId) {
        // SQL 쿼리 ( faq_id, 질문, 답변, 카테고리, 생성일을 faq_id로 조회한다.)
        String sql = "SELECT faq_id, question, answer, category, created_at " +
                "FROM faqs WHERE faq_id = ?";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // try-catch 구문을 통해서 예외 처리
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, faqId); // 첫 번째 ? -> faq_id 구문
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                        String.valueOf(rs.getInt("faq_id")),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getString("category"),
                        rs.getString("created_at")
                };
            }
            // Error 가 발생 시 오류 출력
        } catch (Exception e) {
            System.out.println("FAQ 상세 조회 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return null;
    }

    // FAQ 업데이트하기
    public void updateFaq(int faqId, String newQuestion, String newAnswer, String newCategory) {
        // 질문, 답변, 카테고리를 업데이트한다.
        String sql = "UPDATE faqs SET question = ?, answer = ?, category = ? WHERE faq_id = ?";

        // UPDATE 구문을 작성했기 때문에 ResultSet을 사용할 필요 없다.
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newQuestion); // 첫 번째 ? -> question
            pstmt.setString(2, newAnswer); // 두 번째 ? -> answer
            pstmt.setString(3, newCategory); // 세 번째 ? -> category
            pstmt.setInt(4, faqId); // 네 번째 ? -> faq_id
            pstmt.executeUpdate();
            // Error 발생 시 오류 도출
        } catch (Exception e) {
            System.out.println("FAQ 수정 실패: " + e.getMessage());
            // 사용한 리소스 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }

    // FAQ 삭제하기
    public void deleteFaq(int faqId) {
        //SQL 쿼리문 (faq_id를 통해서 삭제한다)
        String sql = "DELETE FROM faqs WHERE faq_id = ?";

        // DELETE 쿼리라 ResultSet 을 사용하지 않아도 된다.
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, faqId); // 첫 번째 ? -> faqId
            pstmt.executeUpdate();
            // Error 발생 시 오류 구문 출력
        } catch (Exception e) {
            System.out.println("FAQ 삭제 실패: " + e.getMessage());
            // 사용한 자원 정리
        } finally {
            DBConnection.close(pstmt, conn);
        }
    }
}