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


    // 1. 카테고리 목록 조회
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

    // 2. 카테고리 존재 여부 확인
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

    // 3. 카테고리별 강의 목록 조회
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
        // list에 반환
        return list;



    }

    // 4. 강의 상세 조회
    // String의 반환 타입이며, 어떤 강의를 조회할지 ID를 받는다.

    /* comment. <전체적인 흐름 요약>
        1. lectureId 받기
        2. SQL로 DB 조회
        3. 결과 String[] 배열로 반환
        4. 못 찾으면 null
     */
    public String[] getLectureDetail(int lectureId) {

        // 3개의 테이블을 JOIN 함 [강의정보, 강사 닉네임 가져오기, 카테고리 이름 가져오기 (삭제된 강의는 제외)
        String sql = "SELECT l.lecture_id, l.title, l.description, " +
                "m.nickname AS instructor_name, l.view_count, l.like_count, " +
                "c.category_name " +
                "FROM lectures l " +
                "JOIN members m ON l.instructor_id = m.member_id " +
                "JOIN categories c ON l.category_id = c.category_id " +
                "WHERE l.lecture_id = ? AND l.is_deleted = FALSE";

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 에러 방지를 위한 try-catch 구문
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lectureId); // ? 자리에 lectureId 삽입
            rs = pstmt.executeQuery();

            if (rs.next()) { // while이 아니라 if - 강의 하나만 조회
                return new String[]{
                        String.valueOf(rs.getInt("lecture_id")),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("instructor_name"),
                        String.valueOf(rs.getInt("view_count")),
                        String.valueOf(rs.getInt("like_count")),
                        rs.getString("category_name")
                };
            }
            // 에러 원인 확인 및 사용한 자원 회수
        } catch (Exception e) {
            System.out.println("강의 상세 조회 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return null;
    }

    // 5. 조회수 증가

    /* comment. 전체적인 흐름
        1. lectureId 받기
        2. DB에 해당 강의 view_count 1씩 더하기
        3. 반환값은 존재하지 않음
     */

    public void increaseViewCount(int lectureId) {
        // SQL 구문 : DB에서 현재 값에 1을 더해서 저장하는 방식. 이 프로젝트가 아닌 DB안에서 처리하는 용도
        String sql = "UPDATE lectures SET view_count = view_count + 1 WHERE lecture_id = ?";

        // Update는 결과를 읽어올 내용이 없기 떄문에 ResultSet rs 제거
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lectureId); // ? 자리에 lectureId 넣기

            // executeUpdate 를 사용하는 이유
            // SELECT 가 아닌 INSERT, UPDATE, DELETE 를 사용할 때는 executeUpdate를 사용
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("조회수 증가 실패: " + e.getMessage());
        } finally {
            DBConnection.close(null, pstmt, conn);
        }
    }

    // 6. 강의 목록 정렬

    /* comment.
        <역할>
        기존 getLecturesByCategory() 와 유사하지만, SQL 쿼리에서 ORDER BY를 통해 정렬을 한다.
        사용자가 선택한 최신순, 조회수순, 좋아요순으로 정렬을 한다.
        <영향>
        1. ContentInputView.inputSortOption() 에서 정렬 옵션 선택받기
        2. ContentController.showLecturesSorted()
        3. ContentDAO.getLecturesSorted()
        4. ContentOutputView.showLectures() : 기존 목록 출력 재사용 가능
     */

    public List<String[]> getLecturesSorted(int categoryId, String sortType) {
        List<String[]> list = new ArrayList<>();

        // 스위치 문을 통해서 정렬 방식 선정
        String orderBy;
        switch (sortType) {
            case "2": orderBy = "l.view_count DESC"; break;
            case "3": orderBy = "l.like_count DESC"; break;
            default:  orderBy = "l.created_at DESC"; break;
        }

        // SQL 구문 (정렬 순서를 ORDER BY로 정렬)
        // SQL 안의 ? 로 ORDER BY 를 작성하게 되면 DB 가 문자열로 인식해서
        // 정렬이 되지 않는 상황을 알게 되었다.
        String sql = "SELECT l.lecture_id, l.title, m.nickname AS instructor_name, " +
                "l.view_count, l.like_count " +
                "FROM lectures l " +
                "JOIN members m ON l.instructor_id = m.member_id " +
                "WHERE l.category_id = ? AND l.is_deleted = FALSE " +
                "ORDER BY " + orderBy;

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] row = {
                        String.valueOf(rs.getInt("lecture_id")),
                        rs.getString("title"),
                        rs.getString("instructor_name"),
                        String.valueOf(rs.getInt("view_count")),
                        String.valueOf(rs.getInt("like_count"))
                };
                list.add(row);
            }
        } catch (Exception e) {
            System.out.println("강의 정렬 조회 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return list;
    }

    // 7. 키워드 검색

    /* comment.
        <역할>
        사용자가 입력한 키워드로 강의를 검색하는 역할.
        LIKE SQL 쿼리문을 통해서 제목에 키워드가 포함된 강의를 전부 검색한다.
        여기에 정렬까지 같이 붙어있어서 검색 + 정렬을 동시에 진행한다.
        <영향>
        ContentInputView.inputKeyword()
        ContentController.searchLectures()
        ContentDAO.searchLectures()
        ContentOutputView.showLectures() / 기존 출력 재사용
        InputValidator 가 여기서 처음 사용된다.
        빈값이나 특수문자만 입력하면 DB 까지 가지 않고 막아버리는 역할이다.
     */

    public List<String[]> searchLectures(String keyword, String sortType) {
        List<String[]> list = new ArrayList<>();

        // switch 구문을 사용해서 3가지 선택지를 준다.
        String orderBy;
        switch (sortType) {
            case "2": orderBy = "l.view_count DESC"; break;
            case "3": orderBy = "l.like_count DESC"; break;
            default:  orderBy = "l.created_at DESC"; break;
        }

        // SQL 쿼리문 (정렬과 검색을 동시에 하기 때문에 ORDER BY 구문이 있다)
        String sql = "SELECT l.lecture_id, l.title, m.nickname AS instructor_name, " +
                "l.view_count, l.like_count " +
                "FROM lectures l " +
                "JOIN members m ON l.instructor_id = m.member_id " +
                "WHERE l.title LIKE ? AND l.is_deleted = FALSE " +
                "ORDER BY " + orderBy;

        // DB 3총사
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 예외 처리를 위해 try-catch 구문 작성
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            // while 반복문을 통해서 다음 내용이 있으면 계속 반복하는 내용을 진행
            while (rs.next()) {
                String[] row = {
                        String.valueOf(rs.getInt("lecture_id")),
                        rs.getString("title"),
                        rs.getString("instructor_name"),
                        String.valueOf(rs.getInt("view_count")),
                        String.valueOf(rs.getInt("like_count"))
                };
                // 리스트에 추가
                list.add(row);
            }
            // Error 발생을 확인하기 위해 사용
        } catch (Exception e) {
            System.out.println("강의 검색 실패: " + e.getMessage());
            // 사용한 리소스 자원 정리
        } finally {
            DBConnection.close(rs, pstmt, conn);
        }
        return list;
    }

}