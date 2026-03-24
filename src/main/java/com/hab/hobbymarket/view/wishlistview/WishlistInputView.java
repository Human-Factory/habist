package com.hab.hobbymarket.view.wishlistview;

import com.hab.hobbymarket.controller.WishlistController;
import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class WishlistInputView {
    // Controller에 사용자 입력을 전달
    private final WishlistController wishlistController;

    // 콘솔 입력용 Scanner
    private final Scanner sc = ScannerUtil.getInstance();

    // 생성자
    public WishlistInputView(WishlistController wishlistController) {
        this.wishlistController = wishlistController;
    }

    // 관심목록 메뉴
    public void showWishlistMenu() {

        // 사용자가 뒤로가기 할 때까지 반복
        while (true) {
            System.out.println("\n========== 관심목록 ==========");
            System.out.println("1. 관심목록 조회");
            System.out.println("2. 관심목록 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택 : ");

            // 입력값 받기
            String menu = sc.nextLine().trim();

            switch (menu) {

                // 1 입력 시 내 관심목록 조회
                case "1" -> wishlistController.showMyWishlist();

                // 2 입력 시 관심목록 번호를 받아 삭제
                case "2" -> {
                    System.out.print("삭제할 관심번호 입력 : ");

                    // 문자열 입력을 int로 변환
                    int wishlistId = Integer.parseInt(sc.nextLine());

                    // Controller에 삭제 요청
                    wishlistController.deleteWishlist(wishlistId);
                }

                // 0 입력 시 상위 메뉴로 이동
                case "0" -> {
                    return;
                }

                // 그 외 값은 오류 메시지
                default -> System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }
}
