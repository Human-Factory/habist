package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Wishlist;
import com.hab.hobbymarket.service.WishlistService;

import java.util.List;

public class WishlistController {

    private final WishlistService wishlistService = new WishlistService() {
        public List<Wishlist> findByMemberId(int memberId) {
            // Service에 memberId를 넘겨서 관심목록 조회
            return wishlistService.findByMemberId(memberId);

        }

    };
}
