package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.LectureDAO;
import com.hab.hobbymarket.dao.LectureDAOImpl;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

public class LectureService {

    // 강의 DB 작업을 처리할 DAO
    private LectureDAO lectureDAO = new LectureDAOImpl();

    public boolean createLecture(LectureCreateRequest request) {

        // 1. 로그인 여부 확인
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) {
            System.out.println("로그인이 필요합니다.");
            return false;
        }

        // 2. 강의자 권한 확인
        // 현재 프로젝트에서는 role이 INSTRUCTOR인 경우만 강의 등록 가능
        if (!Member.ROLE_INSTRUCTOR.equals(loginUser.getRole())) {
            System.out.println("강의자만 강의를 등록할 수 있습니다.");
            return false;
        }

        // 3. 제목 검증
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            System.out.println("강의 제목을 입력해주세요.");
            return false;
        }

        // 4. 설명 검증
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            System.out.println("강의 설명을 입력해주세요.");
            return false;
        }

        // 5. 카테고리 번호 검증
        if (request.getCategoryId() <= 0) {
            System.out.println("올바른 카테고리 번호를 입력해주세요.");
            return false;
        }

        // 6. DAO 호출
        // instructorId는 사용자가 입력하는 값이 아니라 현재 로그인 사용자 기준으로 넣는다.
        int result = lectureDAO.insertLecture(loginUser.getMemberId(), request);

        // 7. 결과 반환
        return result > 0;
    }
}