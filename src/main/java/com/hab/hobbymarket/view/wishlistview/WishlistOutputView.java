package com.hab.hobbymarket.view.wishlistview;

import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class WishlistOutputView {

    // 관심목록 출력
    public static void printWishlist(List<Wishlist> wishlist) {

        // 조회 결과가 없으면 안내 문구 출력
        if (wishlist.isEmpty()) {
            System.out.println("관심목록이 없습니다.");
            return;
        }

        // 조회 결과가 있으면 하나씩 출력
        for (Wishlist item : wishlist) {
            System.out.println(item);
        }
    }
}