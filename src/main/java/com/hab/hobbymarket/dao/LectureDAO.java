package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.LectureCreateRequest;

public interface LectureDAO {

    /**
     * 강의 등록
     * @param instructorId 현재 로그인한 강의자 회원 번호
     * @param request      강의 등록 입력값 DTO
     * @return             INSERT 성공 행 수
     */
    int insertLecture(long instructorId, LectureCreateRequest request);
}