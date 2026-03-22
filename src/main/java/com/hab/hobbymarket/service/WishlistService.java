package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.WishlistDAO;
import com.hab.hobbymarket.model.Wishlist;

import java.sql.Connection;
import java.util.List;

public class WishlistService {
    // DAO를 통해 실제 DB 작업 수행
    private final WishlistDAO wishlistDAO;

    // Service 생성 시 Connection 받아서 DAO 생성
    public WishlistService(Connection con) {
        this.wishlistDAO = new WishlistDAO(con);
    }

    // --------------------------------------------------------------------
    // 관심목록 추가
    // Controller는 "추가해줘"만 요청하고,
    // 실제 DB 저장은 DAO에 맡김
    // --------------------------------------------------------------------
    public boolean addWishlist(int memberId, int lectureId) {
        return wishlistDAO.insert(memberId, lectureId);
    }

    // --------------------------------------------------------------------
    // 내 관심목록 조회
    // memberId 기준으로 조회
    // --------------------------------------------------------------------
    public List<Wishlist> getWishlist(int memberId) {
        return wishlistDAO.findByMemberId(memberId);
    }

    // --------------------------------------------------------------------
    // 관심목록 삭제
    // memberId와 wishlistId를 같이 넘겨서
    // 본인 데이터만 삭제되도록 처리
    // --------------------------------------------------------------------
    public boolean removeWishlist(int memberId, int wishlistId) {
        return wishlistDAO.deleteByWishlistIdAndMemberId(wishlistId, memberId);
    }
}
