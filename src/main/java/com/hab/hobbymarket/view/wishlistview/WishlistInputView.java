package com.hab.hobbymarket.view.wishlistview;

<<<<<<< HEAD
=======
import com.hab.hobbymarket.controller.WishlistController;
>>>>>>> ea1729a79b358d43cc1ab27c8bf2d6c7cb03e929
import java.util.Scanner;

public class WishlistInputView {

<<<<<<< HEAD
    public static int inputMyPageMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== 관심목록 메뉴 =====");
        System.out.println("1. 관심목록 조회");
        System.out.println("2. 관심목록 삭제");
        System.out.println("0. 뒤로가기");
        System.out.print("선택: ");

        return sc.nextInt();
    }

=======
    private WishlistController wishlistController;
    private Scanner sc = new Scanner(System.in);

    public WishlistInputView(WishlistController wishlistController) {
        this.wishlistController = wishlistController;
    }
>>>>>>> ea1729a79b358d43cc1ab27c8bf2d6c7cb03e929
}
