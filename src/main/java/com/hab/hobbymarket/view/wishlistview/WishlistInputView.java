package com.hab.hobbymarket.view.wishlistview;

import java.util.Scanner;

public class WishlistInputView {

    public static int inputMyPageMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== 관심목록 메뉴 =====");
        System.out.println("1. 관심목록 조회");
        System.out.println("2. 관심목록 삭제");
        System.out.println("0. 뒤로가기");
        System.out.print("선택: ");

        return sc.nextInt();
    }

}
