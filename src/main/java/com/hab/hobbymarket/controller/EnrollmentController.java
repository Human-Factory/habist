package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Enrollment;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.service.EnrollmentService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.enrollmentview.EnrollmentOutputView;

import java.util.List;

public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final EnrollmentOutputView outputView = new EnrollmentOutputView();

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 학습 목록 조회
    public void showMyLearningProgress() {

        // 로그인 체크
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인 회원
        Member user = SessionManager.getCurrentUser();

        int memberId = user.getMemberId().intValue();

        // 서비스 호출
        List<Enrollment> list = enrollmentService.getMyEnrollments(memberId);

        // 출력
        outputView.showEnrollmentList(list);
    }

    // 이어보기
    public void continueLearning(int enrollmentId) {

        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        Member user = SessionManager.getCurrentUser();

        int memberId = user.getMemberId().intValue();

        Enrollment enrollment = enrollmentService.getMyEnrollment(enrollmentId, memberId);

        if (enrollment == null) {
            System.out.println("학습 정보 없음");
            return;
        }

        System.out.println("이어보기 시작");
        System.out.println("강의: " + enrollment.getLectureTitle());
        System.out.println("마지막 위치: " + enrollment.getLastPosition());
        System.out.println("진척도: " + enrollment.getProgressPercent() + "%");
    }
}
