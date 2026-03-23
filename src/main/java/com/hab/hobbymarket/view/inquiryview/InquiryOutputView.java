package com.hab.hobbymarket.view.inquiryview;

import com.hab.hobbymarket.model.Inquiry;

import java.util.List;

public class InquiryOutputView {

    // 목록 출력
    public void showInquiryList(List<Inquiry> list) {
        if (list.isEmpty()) {
            System.out.println("등록된 문의가 없습니다.");
            return;
        }
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
