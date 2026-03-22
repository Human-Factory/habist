package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Wishlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    // DB 연결 객체
    // Service에서 Connection을 받아서 DAO가 사용
    private final Connection con;

    // 생성자를 통해 Connection 주입
    public WishlistDAO(Connection con) {
        this.con = con;
    }

    // 1) 이미 관심목록에 있는지 확인하는 메서드
    public boolean existsByMemberIdAndLectureId(int memberId, int lectureId) {

        // member_id와 lecture_id가 동시에 같은 데이터가 있는지 확인
        String sql = "SELECT COUNT(*) FROM wishlists WHERE member_id = ? AND lecture_id = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? 에 memberId 바인딩
            pstmt.setInt(1, memberId);

            // 두 번째 ? 에 lectureId 바인딩
            pstmt.setInt(2, lectureId);

            // SELECT 문 실행
            rs = pstmt.executeQuery();

            // 결과가 한 줄 나오면 COUNT(*) 값 확인
            if (rs.next()) {
                // 개수가 1 이상이면 이미 존재
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            System.out.println("관심목록 중복 확인 실패: " + e.getMessage());
        } finally {
            // ResultSet, PreparedStatement 닫기
            DBConnection.close(rs, pstmt);
        }

        // 오류나 결과 없음이면 false
        return false;
    }

    // 2) 관심목록 추가
    public boolean insert(int memberId, int lectureId) {

        // 먼저 중복 여부 확인
        // 같은 회원이 같은 강의를 여러 번 찜하지 못하게 막음
        if (existsByMemberIdAndLectureId(memberId, lectureId)) {
            return false;
        }

        // 관심목록 테이블에 member_id, lecture_id 저장
        String sql = "INSERT INTO wishlists (member_id, lecture_id) VALUES (?, ?)";

        PreparedStatement pstmt = null;

        try {
            // INSERT 문 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째 ? -> memberId
            pstmt.setInt(1, memberId);

            // 두 번째 ? -> lectureId
            pstmt.setInt(2, lectureId);

            // executeUpdate()는 INSERT/UPDATE/DELETE에 사용
            // 성공하면 영향받은 행 수가 1 이상
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("관심목록 추가 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(pstmt);
        }
    }

    // 3) 회원 번호로 관심목록 전체 조회
    public List<Wishlist> findByMemberId(int memberId) {

        // 결과를 담을 리스트 생성
        List<Wishlist> list = new ArrayList<>();

        // wishlists와 lectures를 JOIN 해서
        // 관심목록 번호, 회원번호, 강의번호, 강의제목, 생성시간을 가져옴
        String sql = """
                SELECT w.wishlist_id,
                       w.member_id,
                       w.lecture_id,
                       l.title,
                       w.created_at
                FROM wishlists w
                JOIN lectures l ON w.lecture_id = l.lecture_id
                WHERE w.member_id = ?
                ORDER BY w.wishlist_id DESC
                """;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // SQL 준비
            pstmt = con.prepareStatement(sql);

            // 로그인한 회원 번호를 조건으로 넣음
            pstmt.setInt(1, memberId);

            // SELECT 실행
            rs = pstmt.executeQuery();

            // 결과를 한 줄씩 읽으면서 Wishlist 객체로 변환
            while (rs.next()) {

                // ResultSet에서 각 컬럼 값 꺼내서 객체 생성
                Wishlist wishlist = new Wishlist(
                        rs.getInt("wishlist_id"),   // 관심목록 번호
                        rs.getInt("member_id"),     // 회원 번호
                        rs.getInt("lecture_id"),    // 강의 번호
                        rs.getString("title"),      // 강의 제목
                        rs.getTimestamp("created_at") // 생성 시간
                );

                // 리스트에 추가
                list.add(wishlist);
            }

        } catch (Exception e) {
            System.out.println("관심목록 조회 실패: " + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt);
        }

        // 조회된 리스트 반환
        return list;
    }

    // 4) 관심목록 삭제
    public boolean deleteByWishlistIdAndMemberId(int wishlistId, int memberId) {

        // 왜 member_id 조건도 같이 거냐?
        // -> 다른 사람 관심목록을 지우지 못하게 하기 위해서
        String sql = "DELETE FROM wishlists WHERE wishlist_id = ? AND member_id = ?";

        PreparedStatement pstmt = null;

        try {
            // DELETE 문 준비
            pstmt = con.prepareStatement(sql);

            // 첫 번째  -> 삭제할 관심목록 번호
            pstmt.setInt(1, wishlistId);

            // 두 번째  -> 현재 로그인한 회원 번호
            pstmt.setInt(2, memberId);

            // 성공 시 삭제된 행 수가 1 이상
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("관심목록 삭제 실패: " + e.getMessage());
            return false;
        } finally {
            DBConnection.close(pstmt);
        }
    }
}

