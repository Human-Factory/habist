package com.hab.hobbymarket.view.messageview;

import com.hab.hobbymarket.model.Message;

import java.util.List;

public class MessageOutputView {

    // 단순 메시지 출력
    public void printMessage(String message) {
        System.out.println("[알림] " + message);
    }

    // 에러 메시지 출력 (눈에 띄게!)
    public void printError(String errorMessage) {
        System.err.println("[오류] " + errorMessage);
    }

    // 쪽지 목록 출력 (리스트 형태)
    public void displayMessages(List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            System.out.println("\n--- 쪽지 내역이 없습니다 ---");
            return;
        }

        System.out.println("\n================ 쪽지 목록 ================");
        System.out.printf("%-5s | %-10s | %-10s | %-20s | %-10s\n",
                "ID", "보낸이", "받는이", "내용", "읽음여부");
        System.out.println("------------------------------------------");

        for (Message msg : messages) {
            System.out.printf("%-5d | %-10d | %-10d | %-20s | %-10s\n",
                    msg.getMessageId(),
                    msg.getSenderId(),
                    msg.getReceiverId(),
                    truncateContent(msg.getContent()), // 긴 내용은 생략 처리
                    msg.isRead() ? "읽음" : "안읽음"
            );
        }
        System.out.println("==========================================\n");
    }

    // 내용이 너무 길면 잘라서 보여주는 보조 메소드
    private String truncateContent(String content) {
        if (content.length() > 15) {
            return content.substring(0, 12) + "...";
        }
        return content;
    }
}
