package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.InquiryDAO;
import com.hab.hobbymarket.model.Inquiry;
import com.hab.hobbymarket.service.InquiryService;
import com.hab.hobbymarket.session.SessionManager;

import java.util.List;

public class InquiryController {

    // 1. Service 선언 + 생성자
    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 2. 문의 목록 조회
    // → Service 호출해서 결과 그대로 반환
    public List<Inquiry> getMyInquiries() {
        return inquiryService.getMyInquiries();
    }

}
