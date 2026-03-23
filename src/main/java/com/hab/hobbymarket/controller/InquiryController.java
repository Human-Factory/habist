package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Inquiry;
import com.hab.hobbymarket.service.InquiryService;

import java.util.List;

public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 문의 작성
    public boolean save(String title, String content) {
        boolean result = inquiryService.save(title, content);
        if (result) {
            System.out.println("✅ 문의가 등록되었습니다.");
        } else {
            System.out.println("🚨 문의 등록에 실패했습니다.");
        }
        return result;
    }

    // 내 문의 목록 조회
    public List<Inquiry> getMyInquiries() {
        return inquiryService.getMyInquiries();
    }

    // 문의 수정
    public boolean update(Long inquiryId, String title, String content) {
        boolean result = inquiryService.update(inquiryId, title, content);
        if (result) {
            System.out.println("✅ 문의가 수정되었습니다.");
        } else {
            System.out.println("🚨 문의 수정에 실패했습니다.");
        }
        return result;
    }

    // 문의 삭제
    public boolean delete(Long inquiryId) {
        boolean result = inquiryService.delete(inquiryId);
        if (result) {
            System.out.println("✅ 문의가 삭제되었습니다.");
        } else {
            System.out.println("🚨 문의 삭제에 실패했습니다.");
        }
        return result;
    }
}
