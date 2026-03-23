package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.LectureDAO;
import com.hab.hobbymarket.dao.LectureDAOImpl;
import com.hab.hobbymarket.model.Lecture;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.LectureUpdateRequest;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

import java.util.List;

public class LectureService {

    // 강의 DB 작업을 처리할 DAO
    private LectureDAO lectureDAO = new LectureDAOImpl();

    // ======================
    // 강의 등록
    // ======================
    public boolean createLecture(LectureCreateRequest request) {

        // 1. 로그인 여부 확인
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) {
            System.out.println("로그인이 필요합니다.");
            return false;
        }

        // 2. 강의자 권한 확인
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
        int result = lectureDAO.insertLecture(loginUser.getMemberId(), request);

        return result > 0;
    }

    // ======================
    // 강의 수정
    // ======================
    public boolean updateLecture(LectureUpdateRequest request) {

        // 1. 로그인 여부 확인
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) {
            System.out.println("로그인이 필요합니다.");
            return false;
        }

        // 2. 입력값 검증
        if (request.getLectureId() <= 0) {
            System.out.println("올바른 강의 번호를 입력해주세요.");
            return false;
        }

        if (request.getCategoryId() <= 0) {
            System.out.println("올바른 카테고리 번호를 입력해주세요.");
            return false;
        }

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            System.out.println("강의 제목을 입력해주세요.");
            return false;
        }

        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            System.out.println("강의 설명을 입력해주세요.");
            return false;
        }

        int result = 0;

        // 3. 관리자면 모든 강의 수정 가능
        if (Member.ROLE_ADMIN.equals(loginUser.getRole())) {
            result = lectureDAO.updateLectureByAdmin(request);

            // 4. 강의자면 자기 강의만 수정 가능
        } else if (Member.ROLE_INSTRUCTOR.equals(loginUser.getRole())) {
            result = lectureDAO.updateLectureByInstructor(loginUser.getMemberId(), request);

            // 5. 그 외 권한 없음
        } else {
            System.out.println("강의를 수정할 권한이 없습니다.");
            return false;
        }

        return result > 0;
    }

    // ======================
    // 강의 삭제
    // ======================
    public boolean deleteLecture(int lectureId) {

        // 1. 로그인 여부 확인
        Member loginUser = SessionManager.getCurrentUser();
        if (loginUser == null) {
            System.out.println("로그인이 필요합니다.");
            return false;
        }

        // 2. 강의 번호 검증
        if (lectureId <= 0) {
            System.out.println("올바른 강의 번호를 입력해주세요.");
            return false;
        }

        int result = 0;

        // 3. 관리자면 모든 강의 삭제 가능
        if (Member.ROLE_ADMIN.equals(loginUser.getRole())) {
            result = lectureDAO.softDeleteLectureByAdmin(lectureId);

            // 4. 강의자면 자기 강의만 삭제 가능
        } else if (Member.ROLE_INSTRUCTOR.equals(loginUser.getRole())) {
            result = lectureDAO.softDeleteLectureByInstructor(loginUser.getMemberId(), lectureId);

            // 5. 그 외 권한 없음
        } else {
            System.out.println("강의를 삭제할 권한이 없습니다.");
            return false;
        }

        return result > 0;
    }
    public List<Lecture> getAllLectures() {
        return lectureDAO.findAllLectures();
    }
}