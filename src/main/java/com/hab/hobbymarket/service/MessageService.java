package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.MemberDAO;
import com.hab.hobbymarket.dao.MemberDAOImpl;
import com.hab.hobbymarket.dao.MessageDAO;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Message;

import java.util.List;

public class MessageService {

    private final MessageDAO messageDAO;
    private final MemberDAO memberDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.memberDAO  = new MemberDAOImpl();
    }

    // 닉네임으로 수신자를 찾아서 쪽지 보내기 (임시 방식 - 게시판/댓글 연동 전)
    public boolean sendMessageByNickname(Long senderId, String receiverNickname, String content) {
        // 1. 내용 검증
        if (content == null || content.isBlank()) {
            System.out.println("쪽지 내용을 입력해 주세요.");
            return false;
        }

        // 2. 닉네임으로 수신자 조회
        Member receiver = memberDAO.findByNickname(receiverNickname);
        if (receiver == null) {
            System.out.println("존재하지 않는 닉네임입니다: " + receiverNickname);
            return false;
        }

        // 3. 자기 자신에게 쪽지 전송 방지
        if (receiver.getMemberId().equals(senderId)) {
            System.out.println("자기 자신에게는 쪽지를 보낼 수 없습니다.");
            return false;
        }

        // 4. 쪽지 전송
        return messageDAO.save(senderId, receiver.getMemberId(), content);
    }

    // 받은 쪽지 목록 조회
    public List<Message> getReceivedMessages(Long receiverId) {
        return messageDAO.findReceivedMessages(receiverId);
    }

    // 보낸 쪽지 목록 조회
    public List<Message> getSentMessages(Long senderId) {
        return messageDAO.findSentMessages(senderId);
    }

    // 쪽지 읽음 처리
    public boolean readMessage(Long messageId, Long receiverId) {
        return messageDAO.markAsRead(messageId, receiverId);
    }
}

