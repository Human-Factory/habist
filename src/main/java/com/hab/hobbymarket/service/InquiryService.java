package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.InquiryDAO;
import com.hab.hobbymarket.model.Inquiry;
import com.hab.hobbymarket.session.SessionManager;

import java.util.List;

public class InquiryService {

    private final InquiryDAO inquiryDAO = new InquiryDAO();

    // 문의 작성
    public boolean save(String title, String content) {
        Long memberId = SessionManager.getCurrentUser().getMemberId();
        Inquiry inquiry = new Inquiry(memberId, title, content);
        return inquiryDAO.save(inquiry);
    }

    // 내 문의 목록 조회
    public List<Inquiry> getMyInquiries() {
        Long memberId = SessionManager.getCurrentUser().getMemberId();
        return inquiryDAO.findByMemberId(memberId);
    }

    // 문의 수정
    public boolean update(Long inquiryId, String title, String content) {
        Long memberId = SessionManager.getCurrentUser().getMemberId();
        return inquiryDAO.update(inquiryId, memberId, title, content);
    }

    // 문의 삭제
    public boolean delete(Long inquiryId) {
        Long memberId = SessionManager.getCurrentUser().getMemberId();
        return inquiryDAO.delete(inquiryId, memberId);
    }
}
