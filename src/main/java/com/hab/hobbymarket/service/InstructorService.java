package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.InstructorDAO;
import com.hab.hobbymarket.dao.InstructorDAOImpl;
import com.hab.hobbymarket.model.InstructorApplication;

import java.time.LocalDateTime;
import java.util.List;

public class InstructorService {

    private InstructorDAO instructorDAO = new InstructorDAOImpl();

    // 강의자 신청 (REQ-INS-003)
    public void apply(Long memberId) {
        InstructorApplication application = new InstructorApplication(memberId);
        instructorDAO.save(application);
        System.out.println("강의자 신청이 완료되었습니다.");
    }

    // 신청 목록 조회 - 관리자용 (REQ-INS-004)
    public List<InstructorApplication> getApplicationList() {
        return instructorDAO.findAll();
    }

    // 승인/거절 처리 - 관리자용 (REQ-INS-004)
    public void processApplication(Long applicationId, Long memberId, String status) {
        // 1. 신청 상태 변경
        instructorDAO.updateStatus(applicationId, status, LocalDateTime.now());
        // 2. 승인이면 role도 INSTRUCTOR로 변경
        if (status.equals(InstructorApplication.STATUS_APPROVED)) {
            instructorDAO.updateMemberRole(memberId, "INSTRUCTOR");
        }
        System.out.println("처리 완료: " + status);
    }

    // 내 신청 상태 조회 (REQ-INS-005)
    public InstructorApplication getMyApplication(Long memberId) {
        return instructorDAO.findByMemberId(memberId);
    }
}
