package com.hab.hobbymarket.view.inquiryview;

import com.hab.hobbymarket.controller.InquiryController;
import com.hab.hobbymarket.model.Inquiry;

import java.util.List;
import java.util.Scanner;

public class InquiryInputView {

    private final InquiryController inquiryController;
    private final Scanner sc = new Scanner(System.in);

    public InquiryInputView(InquiryController inquiryController) {
        this.inquiryController = inquiryController;
    }

    public void displayInquiryMenu() {
        while (true) {
            System.out.println("\n========== 문의 목록 ==========");
            System.out.println("1. 문의 목록 조회");
            System.out.println("2. 문의 작성");
            System.out.println("3. 문의 수정");
            System.out.println("4. 문의 삭제");
            System.out.println("0. 뒤로가기");
            System.out.println("================================");
            System.out.print("메뉴를 선택해주세요 : ");

            switch (sc.nextLine().trim()) {

                case "1" -> {
                    List<Inquiry> list = inquiryController.getMyInquiries();
                    if (list.isEmpty()) {
                        System.out.println("등록된 문의가 없습니다.");
                    } else {
                        list.forEach(i -> {
                            System.out.println("-----------------------------");
                            System.out.println("번호     : " + i.getInquiryId());
                            System.out.println("제목     : " + i.getTitle());
                            System.out.println("내용     : " + i.getContent());
                            System.out.println("답변여부 : " + (i.isAnswered() ? "✅ 답변완료" : "⏳ 대기중"));
                            System.out.println("답변내용 : " + (i.getAnswer() != null ? i.getAnswer() : "없음"));
                            System.out.println("작성일   : " + i.getCreatedAt());
                        });
                    }
                }

                case "2" -> {
                    System.out.print("제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("내용 입력 : ");
                    String content = sc.nextLine();
                    inquiryController.save(title, content);
                }

                case "3" -> {
                    System.out.print("수정할 문의 번호 입력 : ");
                    Long inquiryId = Long.parseLong(sc.nextLine());
                    System.out.print("새 제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("새 내용 입력 : ");
                    String content = sc.nextLine();
                    inquiryController.update(inquiryId, title, content);
                }

                case "4" -> {
                    System.out.print("삭제할 문의 번호 입력 : ");
                    Long inquiryId = Long.parseLong(sc.nextLine());
                    inquiryController.delete(inquiryId);
                }

                case "0" -> { return; }

                default -> System.out.println("🚨 올바른 번호를 입력해주세요.");
            }
        }
    }
}
