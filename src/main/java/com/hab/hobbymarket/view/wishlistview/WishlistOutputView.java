package com.hab.hobbymarket.view.wishlistview;

import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class WishlistOutputView {

    //관심목록 출력하는 메서드
    public static void printWishlist(List<Wishlist> wishlist) {

        // if문 써서 관심 목록이 있는지 없는지 출력
        if (wishlist.isEmpty()) {
            System.out.println("관심목록이 없습니다.");
            return;
        }

        for (Wishlist item : wishlist) {
            System.out.println(item);
        }
    }
}
