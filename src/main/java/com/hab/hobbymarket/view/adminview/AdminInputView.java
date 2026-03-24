package com.hab.hobbymarket.view.adminview;

import com.hab.hobbymarket.controller.AdminController;
import java.util.Scanner;
public class AdminInputView {

    private static AdminController adminController;

    // 공용 Scanner 사용 (Scanner 충돌 방지)
    private static final Scanner sc = new Scanner(System.in);

    public AdminInputView(AdminController adminController) {
        this.adminController = adminController;
    }

    // 1. 회원 관리 메뉴
    public static void showMemberManageMenu() {

        while (true) {

            System.out.println("\n========== 회원 관리 ==========");
            System.out.println("1. 회원 조회");
            System.out.println("2. 회원 상태 변경");
            System.out.println("0. 뒤로가기");

            String menu = sc.nextLine().trim();

            switch (menu) {

                case "1" -> adminController.showMembers();

                case "2" -> {

                    System.out.print("회원 번호 입력 : ");
                    long memberId = Long.parseLong(sc.nextLine());

                    System.out.println("1. 경고");
                    System.out.println("2. 정지");
                    System.out.println("3. 정지해제");

                    String statusMenu = sc.nextLine().trim();

                    String status;

                    // 상태 값 변환
                    if ("1".equals(statusMenu)) {
                        status = "WARNING";
                    } else if ("2".equals(statusMenu)) {
                        status = "BANNED";
                    } else if ("3".equals(statusMenu)) {
                        status = "ACTIVE";
                    } else {
                        System.out.println("잘못된 입력");
                        continue;
                    }

                    // Controller 호출
                    adminController.changeMemberStatus(memberId, status);
                }

                case "0" -> {
                    return;
                }
            }
        }
    }

    // 2. 공지사항 메뉴
    public static void showNoticeMenu() {

        while (true) {

            System.out.println("\n========== 공지 관리 ==========");
            System.out.println("1. 등록");
            System.out.println("2. 수정");
            System.out.println("3. 삭제");
            System.out.println("4. 조회");
            System.out.println("0. 뒤로가기");

            String menu = sc.nextLine().trim();

            switch (menu) {

                case "1" -> {
                    System.out.print("제목 : ");
                    String title = sc.nextLine();

                    System.out.print("내용 : ");
                    String content = sc.nextLine();

                    adminController.createNotice(title, content);
                }

                case "2" -> {
                    System.out.print("공지번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("제목 : ");
                    String title = sc.nextLine();

                    System.out.print("내용 : ");
                    String content = sc.nextLine();

                    adminController.editNotice(id, title, content);
                }

                case "3" -> {
                    System.out.print("공지번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    adminController.removeNotice(id);
                }

                case "4" -> {
                    adminController.showNotices();
                }

                case "0" -> {
                    return;
                }
            }
        }
    }

    // 3. Q&A 메뉴
    public static void showQnaMenu() {

        while (true) {

            System.out.println("\n========== Q&A 관리 ==========");
            System.out.println("1. 등록");
            System.out.println("2. 수정");
            System.out.println("3. 삭제");
            System.out.println("0. 뒤로가기");

            String menu = sc.nextLine().trim();

            switch (menu) {

                case "1" -> {
                    System.out.print("제목 : ");
                    String title = sc.nextLine();

                    System.out.print("내용 : ");
                    String content = sc.nextLine();

                    adminController.createQna(title, content);
                }

                case "2" -> {
                    System.out.print("번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("제목 : ");
                    String title = sc.nextLine();

                    System.out.print("내용 : ");
                    String content = sc.nextLine();

                    adminController.editQna(id, title, content);
                }

                case "3" -> {
                    System.out.print("번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    adminController.removeQna(id);
                }

                case "0" -> {
                    return;
                }
            }
        }
    }
}