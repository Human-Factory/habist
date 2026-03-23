package com.hab.hobbymarket.view.lectureview;

import com.hab.hobbymarket.model.Lecture;

import java.util.List;

public class LectureOutputView {

    /**
     * 강의 목록 출력
     *
     * @param lectures 조회된 강의 목록
     */
    public void printLectureList(List<Lecture> lectures) {

        if (lectures == null || lectures.isEmpty()) {
            System.out.println("조회된 강의가 없습니다.");
            return;
        }

        System.out.println("===== 강의 목록 =====");

        for (Lecture lecture : lectures) {
            System.out.println(
                    "강의번호: " + lecture.getLectureId()
                            + " | 제목: " + lecture.getTitle()
                            + " | 강사명: " + lecture.getInstructorName()
                            + " | 조회수: " + lecture.getViewCount()
                            + " | 좋아요: " + lecture.getLikeCount()
            );
        }
    }
}