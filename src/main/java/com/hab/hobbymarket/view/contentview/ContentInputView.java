package com.hab.hobbymarket.view.contentview;

import java.util.Scanner;

public class ContentInputView {

    private final Scanner sc = new Scanner(System.in);

    // 카테고리 ID 입력받기
    public int inputCategoryId() {
        System.out.print("\n조회할 카테고리 번호를 입력하세요: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1; // 유효하지 않은 값 반환
        }
    }
}
