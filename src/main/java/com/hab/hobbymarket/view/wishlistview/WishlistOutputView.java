package com.hab.hobbymarket.view.wishlistview;

import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class WishlistOutputView {
    // 관심목록 리스트를 화면에 출력하는 메서드
    public void showWishlist(List<Wishlist> list) {

        System.out.println("\n========== 내 관심목록 ==========");

        // 리스트가 비어 있으면 출력 후 종료
        if (list == null || list.isEmpty()) {
            System.out.println("관심목록이 비어 있습니다.");
            return;
        }

        // 관심목록 하나씩 출력
        for (Wishlist wishlist : list) {

            // 삭제할 때 사용할 관심번호
            System.out.println("관심번호 : " + wishlist.getWishlistId());

            // 어떤 강의인지 구분하기 위한 강의 번호
            System.out.println("강의번호 : " + wishlist.getLectureId());

            // 사용자에게 보여줄 강의 제목
            System.out.println("강의제목 : " + wishlist.getLectureTitle());

            // 관심목록에 추가된 시간
            System.out.println("추가일시 : " + wishlist.getCreatedAt());

            // 구분선
            System.out.println("--------------------------------");
        }
    }
}
