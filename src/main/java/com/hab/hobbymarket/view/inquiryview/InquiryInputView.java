package com.hab.hobbymarket.view.inquiryview;

import com.hab.hobbymarket.controller.InquiryController;
import com.hab.hobbymarket.model.Inquiry;

import java.util.List;
import java.util.Scanner;

public class InquiryInputView {

    // 1. Controller 선언 + Scanner
    private final InquiryController inquiryController;
    private final Scanner sc = new Scanner(System.in);

    // 2. 생성자
    public InquiryInputView(InquiryController inquiryController) {
        this.inquiryController = inquiryController;
    }

    // 3. 문의 메뉴
    public void displayInquiryMenu() {

        // Controller에서 목록 가져오기
        List<Inquiry> list = inquiryController.getMyInquiries();

        // 목록이 비어있으면
        if (list.isEmpty()) {
            System.out.println("등록된 문의가 없습니다.");
            return;
        }

        // 목록 출력
        for (Inquiry inquiry : list) {
            System.out.println("=============================");
            System.out.println("번호     : " + inquiry.getInquiryId());
            System.out.println("제목     : " + inquiry.getTitle());
            System.out.println("내용     : " + inquiry.getContent());
            System.out.println("답변여부 : " + (inquiry.isAnswered() ? "✅ 답변완료" : "⏳ 대기중"));
            System.out.println("답변내용 : " + (inquiry.getAnswer() != null ? inquiry.getAnswer() : "없음"));
            System.out.println("작성일   : " + inquiry.getCreatedAt());
        }
    }
}
