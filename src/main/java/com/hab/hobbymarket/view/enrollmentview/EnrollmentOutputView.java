package com.hab.hobbymarket.view.enrollmentview;

import com.hab.hobbymarket.model.Enrollment;

import java.util.List;

// 화면 출력 전용 클래스
public class EnrollmentOutputView {

    // 학습 목록 출력
    public void showEnrollmentList(List<Enrollment> list) {

        System.out.println("\n========== 학습 진척도 ==========");

        // 조회된 학습 데이터가 없으면 안내 문구 출력
        if (list == null || list.isEmpty()) {
            System.out.println("시청한 강의가 없습니다.");
            return;
        }

        // 조회된 학습 데이터가 있으면 하나씩 출력
        for (Enrollment enrollment : list) {
            System.out.println("수강번호 : " + enrollment.getEnrollmentId());
            System.out.println("강의제목 : " + enrollment.getLectureTitle());
            System.out.println("진척도 : " + enrollment.getProgressPercent() + "%");
            System.out.println("마지막 위치 : " + enrollment.getLastPosition());
            System.out.println("--------------------------------");
        }
    }

    // 이어보기 정보 출력
    public void showContinueLearning(Enrollment enrollment) {

        System.out.println("\n========== 이어보기 ==========");

        // 이어볼 학습 정보가 없으면 안내 문구 출력
        if (enrollment == null) {
            System.out.println("학습 정보가 없습니다.");
            return;
        }

        System.out.println("강의제목 : " + enrollment.getLectureTitle());
        System.out.println("현재 진척도 : " + enrollment.getProgressPercent() + "%");
        System.out.println("마지막 학습 위치 : " + enrollment.getLastPosition());
        System.out.println("--------------------------------");
        System.out.println("이 위치부터 학습을 이어갑니다.");
    }
}