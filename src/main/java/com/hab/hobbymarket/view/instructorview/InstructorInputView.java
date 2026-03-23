package com.hab.hobbymarket.view.instructorview;

import java.util.Scanner;

public class InstructorInputView {

    private Scanner scanner = new Scanner(System.in);

    // 처리할 신청 번호 입력 (관리자용)
    public Long getApplicationId() {
        System.out.print("처리할 신청 번호 입력: ");
        return scanner.nextLong();
    }

    // 승인/거절 선택 (관리자용)
    public String getDecision() {
        System.out.println("1. 승인");
        System.out.println("2. 거절");
        System.out.print("선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            return "APPROVED";
        } else {
            return "REJECTED";
        }
    }
}
