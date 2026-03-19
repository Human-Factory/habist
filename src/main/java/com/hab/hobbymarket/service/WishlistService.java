package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.WishlistDAO;
import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class WishlistService {

    // DAO 객체 생성
    private final WishlistDAO wishlistDAO = new WishlistDAO();

    // 회원 ID로 관심목록 조회
    public List<Wishlist> findByMemberId(int memberId) {

        // DAO 호출해서 조회 결과 반환
        return wishlistDAO.findByMemberId(memberId);
    }
}