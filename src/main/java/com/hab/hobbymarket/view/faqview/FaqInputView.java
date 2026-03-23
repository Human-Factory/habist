package com.hab.hobbymarket.view.faqview;

import com.hab.global.utils.ScannerUtil;

import java.util.Scanner;

public class FaqInputView {

    // 스캐너 객체 생성
    private final Scanner sc = ScannerUtil.getInstance();

    // FAQ ID를 입력해서 출력
    public int inputFaqId() {
        System.out.print("\nFAQ 번호를 입력하세요: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
            // 만약 정수형으로 입력하지 않았다면 아래의 출력문을 출력
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }

    // 질문 입력하기
    public String inputQuestion() {
        System.out.print("\n질문을 입력하세요 (최대 500자): ");
        String input = sc.nextLine().trim();
        // 만약 질문칸이 null 값일 경우 아래의 출력문을 출력
        if (input.isEmpty()) {
            System.out.println("질문을 입력해주세요.");
            return null;
        }
        // 만약 질문의 길이가 500자 이상일 경우 아래의 출력문을 출력
        if (input.length() > 500) {
            System.out.println("질문은 500자 이내로 입력해주세요.");
            return null;
        }
        return input;
    }

    // FAQ 답변 내용
    public String inputAnswer() {
        System.out.print("\n답변을 입력하세요: ");
        String input = sc.nextLine().trim();
        // 만약 null 값이라면 아래의 출력문을 출력
        if (input.isEmpty()) {
            System.out.println("답변을 입력해주세요.");
            return null;
        }
        return input;
    }

    // 카테고리 입력하기
    public String inputCategory() {
        System.out.print("\n카테고리를 입력하세요 (최대 50자, 없으면 Enter): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }
        // 만약 카테고리가 50자 이상이라면 아래의 출력문 출력
        if (input.length() > 50) {
            System.out.println("카테고리는 50자 이내로 입력해주세요.");
            return null;
        }
        return input;
    }
}