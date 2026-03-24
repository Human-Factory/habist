package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Lecture;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.LectureUpdateRequest;
import com.hab.hobbymarket.service.LectureService;
import com.hab.hobbymarket.view.lectureview.LectureOutputView;

import java.util.List;

public class LectureController {

    private final LectureService lectureService = new LectureService();
    private final LectureOutputView lectureOutputView = new LectureOutputView();

    /**
     * 강의 등록 요청 처리
     *
     * @param request 강의 등록 입력값 DTO
     */
    public void createLecture(LectureCreateRequest request) {

        // Service에 등록 요청 전달
        boolean result = lectureService.createLecture(request);

        // 결과에 따라 사용자에게 메시지 출력
        if (result) {
            System.out.println("강의 등록이 완료되었습니다.");
        } else {
            System.out.println("강의 등록에 실패했습니다.");
        }
    }

    /**
     * 강의 수정 요청 처리
     *
     * @param request 강의 수정 입력값 DTO
     */
    public void updateLecture(LectureUpdateRequest request) {

        // Service에 수정 요청 전달
        boolean result = lectureService.updateLecture(request);

        // 결과 메시지 출력
        if (result) {
            System.out.println("강의 수정이 완료되었습니다.");
        } else {
            System.out.println("강의 수정에 실패했습니다.");
        }
    }

    /**
     * 강의 삭제 요청 처리
     *
     * @param lectureId 삭제할 강의 번호
     */
    public void deleteLecture(int lectureId) {

        // Service에 삭제 요청 전달
        boolean result = lectureService.deleteLecture(lectureId);

        // 결과 메시지 출력
        if (result) {
            System.out.println("강의 삭제가 완료되었습니다.");
        } else {
            System.out.println("강의 삭제에 실패했습니다.");
        }
    }
    public void showAllLectures() {
        List<Lecture> lectures = lectureService.getAllLectures();
        lectureOutputView.printLectureList(lectures);
    }

}