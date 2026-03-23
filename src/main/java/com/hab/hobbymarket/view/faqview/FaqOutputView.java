package com.hab.hobbymarket.view.faqview;

import java.util.List;

public class FaqOutputView {

    public void showFaqs(List<String[]> faqs) {
        System.out.println("\n=== FAQ 목록 ===");
        // 만약 FAQ 가 null 이라면 아래의 출력문 출력
        if (faqs.isEmpty()) {
            System.out.println("등록된 FAQ가 없습니다.");
            return;
        }
        // for-each 구문 사용
        // 삼항 연산자 null 처리 - 이렇게 한 이유는 null 을 그냥 print 하면 말 그대로 null 이라고 출력하기 때문에
        for (String[] row : faqs) {
            // row[0]=faq_id, row[1]=question, row[2]=category, row[3]=created_at
            String category = (row[2] != null) ? "[" + row[2] + "] " : "";
            System.out.printf("[%s] %s%s | %s\n",
                    row[0], category, row[1], row[3]);
        }
    }

    // FAQ 상세 조회
    public void showFaqDetail(String[] faq) {
        // 만 faq가 null 이라면 아래의 출력문 출력
        if (faq == null) {
            showError("존재하지 않는 FAQ입니다.");
            return;
        }
        // faq[0]=faq_id, faq[1]=question, faq[2]=answer, faq[3]=category, faq[4]=created_at
        System.out.println("\n=== FAQ 상세 ===");
        System.out.printf("번호      : %s\n", faq[0]);
        System.out.printf("카테고리  : %s\n", (faq[3] != null) ? faq[3] : "없음");
        System.out.printf("질문      : %s\n", faq[1]);
        System.out.printf("답변      : %s\n", faq[2]);
        System.out.printf("작성일    : %s\n", faq[4]);
    }

    public void showFaqAdded() {
        System.out.println("\nFAQ가 등록되었습니다.");
    }

    public void showFaqUpdated() {
        System.out.println("\nFAQ가 수정되었습니다.");
    }

    public void showFaqDeleted() {
        System.out.println("\nFAQ가 삭제되었습니다.");
    }

    public void showError(String message) {
        System.out.println("[오류] " + message);
    }
}