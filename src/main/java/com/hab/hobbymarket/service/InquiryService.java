package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.InquiryDAO;
import com.hab.hobbymarket.model.Inquiry;
import com.hab.hobbymarket.session.SessionManager;

import java.util.List;

public class InquiryService {

    // 1. DAO 선언
    private final InquiryDAO inquiryDAO = new InquiryDAO();

    public List<Inquiry> getMyInquiries() {

        // SessionManager에서 현재 로그인한 유저 memberId 가져오기
        Long memberId = SessionManager.getCurrentUser().getMemberId();

        // DAO 호출해서 결과 반환
        return inquiryDAO.findByMemberId(memberId);

    }

    public boolean save(String title, String content) {
        Long memberId = SessionManager.getCurrentUser().getMemberId(); // memberId 가져오기
        return inquiryDAO.save(memberId, title, content);              // DAO 호출
    }

}
