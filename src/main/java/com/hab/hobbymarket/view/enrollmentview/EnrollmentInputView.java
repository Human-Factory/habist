package com.hab.hobbymarket.view.enrollmentview;

import com.hab.hobbymarket.controller.EnrollmentController;
import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class EnrollmentInputView {
    private final EnrollmentController controller;
    private final Scanner sc = ScannerUtil.getInstance();

    public EnrollmentInputView(EnrollmentController controller) {
        this.controller = controller;
    }

    // 학습 메뉴
    public void showLearningMenu() {
        while (true) {
            System.out.println("\n========== 학습관리 ==========");
            System.out.println("1. 진척도 조회");
            System.out.println("2. 이어보기");
            System.out.println("0. 뒤로가기");

            String menu = sc.nextLine();

            switch (menu) {
                case "1" -> controller.showMyLearningProgress();

                case "2" -> {
                    System.out.print("수강번호 입력: ");
                    int id = Integer.parseInt(sc.nextLine());

                    controller.continueLearning(id);
                }

                case "0" -> {
                    return;
                }
            }
        }
    }
}
