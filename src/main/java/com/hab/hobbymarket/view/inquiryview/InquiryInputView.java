package com.hab.hobbymarket.view.inquiryview;

import com.hab.hobbymarket.controller.InquiryController;
import com.hab.hobbymarket.model.Inquiry;

import java.util.List;
import java.util.Scanner;

public class InquiryInputView {

    private final InquiryController inquiryController;
    private final InquiryOutputView outputView = new InquiryOutputView(); // ← 추가!
    private final Scanner sc = new Scanner(System.in);
    public InquiryInputView(InquiryController inquiryController) {
        this.inquiryController = inquiryController;
    }
    public void displayInquiryMenu() {
        while (true) {
            System.out.println("1. 문의 목록 조회");
            System.out.println("0. 뒤로가기");

            switch (sc.nextLine().trim()) {
                case "1" -> {
                    List<Inquiry> list = inquiryController.getMyInquiries();
                    outputView.showInquiryList(list); // ← OutputView에 위임!
                }
                case "0" -> { return; }
            }
        }
    }

    public void displayInquiryWrite() {

        while (true) {

            System.out.println("1. 문의 작성");
            System.out.println("0. 뒤로가기");

            switch (sc.nextLine().trim()) {

                case "1" -> {
                    System.out.print("제목 입력 : ");
                    String title = sc.nextLine();
                    System.out.print("내용 입력 : ");
                    String content = sc.nextLine();
                    inquiryController.save(title, content);
                }
                case "0" -> { return; }
            }

        }
    }

}
