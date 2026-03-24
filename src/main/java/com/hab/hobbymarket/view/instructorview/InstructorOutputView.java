package com.hab.hobbymarket.view.instructorview;

import com.hab.hobbymarket.model.InstructorApplication;

import java.util.List;

public class InstructorOutputView {

    // 강의자 신청 완료 메시지
    public void printApplySuccess() {
        System.out.println("강의자 신청이 완료되었습니다.");
    }

    // 신청 목록 출력 (관리자용)
    public void printApplicationList(List<InstructorApplication> list) {
        if (list.isEmpty()) {
            System.out.println("신청 목록이 없습니다.");
            return;
        }
        System.out.println("=== 강의자 신청 목록 ===");
        for (InstructorApplication app : list) {
            System.out.println("신청번호: " + app.getApplicationId()
                    + " | 회원ID: " + app.getMemberId()
                    + " | 상태: " + app.getStatus()
                    + " | 신청일: " + app.getCreatedAt());
        }
    }

    // 내 신청 상태 출력 (신청한 회원용)
    public void printMyApplication(InstructorApplication application) {
        if (application == null) {
            System.out.println("강의자 신청 내역이 없습니다.");
            return;
        }
        System.out.println("=== 내 신청 상태 ===");
        System.out.println("신청번호: " + application.getApplicationId());
        System.out.println("상태: " + application.getStatus());
        System.out.println("신청일: " + application.getCreatedAt());
    }

    // 처리 완료 메시지
    public void printProcessSuccess(String status) {
        if (status.equals(InstructorApplication.STATUS_APPROVED)) {
            System.out.println("승인이 완료되었습니다.");
        } else {
            System.out.println("거절이 완료되었습니다.");
        }
    }
}
