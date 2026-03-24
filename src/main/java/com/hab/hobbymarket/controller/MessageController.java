package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Message;
import com.hab.hobbymarket.service.MessageService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.messageview.MessageInputView;
import com.hab.hobbymarket.view.messageview.MessageOutputView;

import java.util.List;

public class MessageController {
    private MessageService messageService;
    private MessageInputView messageInputView;
    private MessageOutputView messageOutputView;

    public MessageController(MessageService messageService,
                             MessageInputView messageInputView,
                             MessageOutputView messageOutputView) {
        this.messageService = messageService;
        this.messageInputView = messageInputView;
        this.messageOutputView = messageOutputView;
    }

    // 시스템의 중심 루프
    public void start() {
        while (true) {
            // [전달 과정] InputView가 리턴한 숫자가 menu 변수에 담김
            int menu = messageInputView.showMenu();

            switch (menu) {
                case 1 -> handleSendMessage();
                case 2 -> handleShowReceivedMessages();
                case 3 -> handleShowSentMessages();
                case 0 -> {
                    return; // 루프 종료 -> 마이페이지로 복귀
                }
                default -> messageOutputView.printError("잘못된 선택입니다.");
            }
        }
    }

    private void handleSendMessage() {
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) return;

        Long senderId = loginUser.getMemberId();
        String receiverNickname = messageInputView.getReceiverNickname();
        String content = messageInputView.getContent();

        boolean isSuccess = messageService.sendMessageByNickname(senderId, receiverNickname, content);

        if (isSuccess) {
            messageOutputView.printMessage("쪽지를 성공적으로 보냈습니다.");
        } else {
            messageOutputView.printError("쪽지 보내기에 실패했습니다.");
        }
    }

    private void handleShowReceivedMessages() {
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) return;

        Long receiverId = loginUser.getMemberId();
        List<Message> messages = messageService.getReceivedMessages(receiverId);
        messageOutputView.displayMessages(messages);
    }

    private void handleShowSentMessages() {
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) return;

        Long senderId = loginUser.getMemberId();
        List<Message> messages = messageService.getSentMessages(senderId);
        messageOutputView.displayMessages(messages);
    }
}