package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.InstructorApplication;
import com.hab.hobbymarket.service.InstructorService;
import com.hab.hobbymarket.view.instructorview.InstructorInputView;
import com.hab.hobbymarket.view.instructorview.InstructorOutputView;

import java.util.List;

public class InstructorController {

    private final InstructorService instructorService = new InstructorService();
    private final InstructorInputView inputView = new InstructorInputView();
    private final InstructorOutputView outputView = new InstructorOutputView();

    // 강의자 신청 - 로그인한 회원이 호출 (REQ-INS-001, 003)
    public void apply(Long memberId) {
        instructorService.apply(memberId);
        outputView.printApplySuccess();
    }

    // 신청 목록 조회 + 승인/거절 - 관리자용 (REQ-INS-004)
    public void processApplication() {
        // 1. 신청 목록 조회
        List<InstructorApplication> list = instructorService.getApplicationList();

        // 2. 목록 출력
        outputView.printApplicationList(list);

        if (list.isEmpty()) return;

        // 3. 처리할 신청 번호 입력
        Long applicationId = inputView.getApplicationId();

        // 4. 해당 신청의 memberId 찾기
        Long memberId = list.stream()
                .filter(app -> app.getApplicationId().equals(applicationId))
                .findFirst()
                .map(InstructorApplication::getMemberId)
                .orElse(null);

        if (memberId == null) {
            System.out.println("존재하지 않는 신청 번호입니다.");
            return;
        }

        // 5. 승인/거절 선택
        String status = inputView.getDecision();

        // 6. 처리
        instructorService.processApplication(applicationId, memberId, status);
        outputView.printProcessSuccess(status);
    }

    // 내 신청 상태 조회 - 신청한 회원용 (REQ-INS-005)
    public void checkMyApplication(Long memberId) {
        InstructorApplication application = instructorService.getMyApplication(memberId);
        outputView.printMyApplication(application);
    }
}
