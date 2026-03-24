package com.hab.hobbymarket.view.messageview;

import com.hab.global.utils.ScannerUtil;
import java.util.Scanner;

public class MessageInputView {

    private final Scanner sc = ScannerUtil.getInstance();

    // 쪽지 메뉴 출력 후 선택 번호 반환
    public int showMenu() {
        System.out.println("\n===== 쪽지 시스템 =====");
        System.out.println("1. 쪽지 보내기");
        System.out.println("2. 받은 쪽지함");
        System.out.println("3. 보낸 쪽지함");
        System.out.println("0. 이전 메뉴(마이페이지)");
        System.out.print("메뉴 선택: ");
        return readInt();
    }

    // 수신자 닉네임 입력 (임시 방식 - 게시판/댓글 연동 전)
    public String getReceiverNickname() {
        System.out.print("받는 사람 닉네임을 입력하세요: ");
        return sc.nextLine().trim();
    }

    // 쪽지 내용 입력
    public String getContent() {
        System.out.print("쪽지 내용을 입력하세요: ");
        return sc.nextLine();
    }

    private int readInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("[오류] 숫자만 입력 가능합니다.");
            return -1;
        }
    }
}
