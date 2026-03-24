package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.Lecture;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.LectureUpdateRequest;

import java.util.List;

public interface LectureDAO {

    // 강의 등록
    int insertLecture(long instructorId, LectureCreateRequest request);

    // 강의 수정 - 강의자 본인
    int updateLectureByInstructor(long instructorId, LectureUpdateRequest request);

    // 강의 수정 - 관리자
    int updateLectureByAdmin(LectureUpdateRequest request);

    // 강의 삭제 - 강의자 본인
    int softDeleteLectureByInstructor(long instructorId, int lectureId);

    // 강의 삭제 - 관리자
    int softDeleteLectureByAdmin(int lectureId);

    // 전체 강의 목록 조회
    List<Lecture> findAllLectures();
}