package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.WishlistController;
import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        // Controller 생성
        WishlistController controller = new WishlistController();

        // 테스트용 memberId (DB에 있는 값)
        int memberId = 1;

        // 관심목록 조회
        List<Wishlist> list = controller.findByMemberId(memberId);

        // 출력
        if (list.isEmpty()) {
            System.out.println("관심목록이 없습니다.");
        } else {
            for (Wishlist w : list) {
                System.out.println(w);
            }
        }
    }
}