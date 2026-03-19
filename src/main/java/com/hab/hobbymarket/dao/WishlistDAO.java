package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Wishlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    public List<Wishlist> findByMemberId(int memberId) {

        // 조회 결과를 담을 리스트
        List<Wishlist> wishlist = new ArrayList<>();

        // DB 연결 객체
        Connection conn = null;

        // SQL 실행 객체
        PreparedStatement pstmt = null;

        // SELECT 결과를 담는 객체
        ResultSet rs = null;

        // 특정 회원의 관심목록 조회 SQL
        String sql = "SELECT wishlist_id, member_id, lecture_id, created_at " +
                "FROM wishlists " +
                "WHERE member_id = ? " +
                "ORDER BY created_at DESC";

        try {
            // DB 연결
            conn = DBConnection.getConnection();

            // SQL 실행 준비
            pstmt = conn.prepareStatement(sql);

            // 첫 번째 ? 자리에 memberId 넣기
            pstmt.setInt(1, memberId);

            // SELECT 실행 결과 받기
            rs = pstmt.executeQuery();

            // 조회된 행이 있으면 한 줄씩 Wishlist 객체에 담기
            while (rs.next()) {

                Wishlist item = new Wishlist();

                item.setWishlistId(rs.getInt("wishlist_id"));
                item.setMemberId(rs.getInt("member_id"));
                item.setLectureId(rs.getInt("lecture_id"));
                item.setCreatedAt(rs.getTimestamp("created_at"));

                // 리스트에 추가
                wishlist.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 최종 조회 결과 반환
        return wishlist;
    }
}
