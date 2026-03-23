package com.hab.hobbymarket.view.instructorview;

import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class InstructorInputView {

    private Scanner scanner = ScannerUtil.getInstance();

    // 처리할 신청 번호 입력 (관리자용)
    public Long getApplicationId() {
        System.out.print("처리할 신청 번호 입력: ");
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1L;
        }
    }

    // 승인/거절 선택 (관리자용)
    public String getDecision() {
        System.out.println("1. 승인");
        System.out.println("2. 거절");
        System.out.print("선택: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                return "APPROVED";
            } else {
                return "REJECTED";
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return "INVALID";
        }
    }
}
