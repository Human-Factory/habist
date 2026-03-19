package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Wishlist;
import com.hab.hobbymarket.service.WishlistService;

import java.util.List;

public class WishlistController {

    // Service 객체 생성
    private final WishlistService wishlistService = new WishlistService();

    // 회원 ID로 관심목록 조회
    public List<Wishlist> findByMemberId(int memberId) {

        // Service 호출해서 관심목록 조회
        return wishlistService.findByMemberId(memberId);
    }
}