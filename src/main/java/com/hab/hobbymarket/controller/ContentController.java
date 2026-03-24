package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.ContentDAO;
import com.hab.hobbymarket.view.contentview.ContentInputView;
import com.hab.hobbymarket.view.contentview.ContentOutputView;

import java.util.List;

public class ContentController {

    /** ContentController 역할
     * <역할>
     *     사용자 입력 -> DAO(DB 조회) -> View(출력) 흐름을 제어하는 컨트롤러
     *
     *  사용자 입력
     *  ContentInputView : 입력만 담당
     *  ContentController : 흐름 제어만 담당
     *  ContentDAO : DB 접근만 담당
     *  ContentOutputView : 출력만 담당
     *
     *  <예외 처리 원칙>
     *      1. 숫자가 아닌 값 입력 inputView 에서 -1 반환 -> Controller 에서 return
     *      2. 존재하지 않는 카테고리 categoryExists() 로 확인 후 showError()
     *      3. 존재하지 않는 강의 getLectureDetail() 가 null 반환 -> showLectureDetail() 에서 처리
     *      4. 빈 키워드 입력 inputKeyword() 가 null 반환 -> Controller 에서 return
     */

    // MVC + DAO 패턴 : Controller는 DAO(데이터)와 View(출력)를 연결하는 역할만 한다
    // MVC 패턴 : 소프트웨어를 데이터(Model) + 사용자 인터페이스(View) + 로직 제어(Controller) 세가지 역할로 분리 개발하는 디자인 패턴
    private final ContentDAO contentDAO = new ContentDAO();
    private final ContentOutputView outputView = new ContentOutputView();
    private final ContentInputView inputView = new ContentInputView();

    // LEC-001 : 카테고리 목록 조회
    /* comment.
        <역할>
        전체 카테고리 목록 조회 후 추력
        <흐름>
        getCategories() -> showCategories()
     */
    public void showCategory() {
        List<String[]> categories = contentDAO.getCategories();
        outputView.showCategories(categories);
    }

    // LEC-002 : 카테고리별 강의 목록 조회
    /* comment.
        <역할>
        카테고리 선택 -> 해당 카테고리의 강의 목록 출력
        <흐름>
        1. 카테고리 목록 출력 (showCategory 재사용)
        2. 카테고리 번호 입력받기
        3. 입력값 유효성 검사 (-1이면 종료)
        4. 카테고리 존재 여부 확인 (없다면 Error 발생)
        5. 강의 목록 조회 & 출력
     */
    public void showLecturesByCategory() {
        // 1. 카테고리 목록 먼저 보여주기
        showCategory();

        // 2. 사용자에게 카테고리 번호 입력받기
        int categoryId = inputView.inputCategoryId();

        // 3. 유효하지 않은 입력값 방어
        if (categoryId == -1) {
            outputView.showError("올바른 카테고리 번호를 입력해주세요.");
            return;
        }

        // 4. 카테고리 존재 여부 확인
        if (!contentDAO.categoryExists(categoryId)) {
            outputView.showError("존재하지 않는 카테고리입니다.");
            return;
        }

        // 5. 강의 목록 조회 및 출력
        List<String[]> lectures = contentDAO.getLecturesByCategory(categoryId);
        outputView.showLectures(lectures);

        // 6. 강의 번호 입력받기
        if (lectures.isEmpty()) return;

        int lectureId = inputView.inputLectureId();
        if (lectureId == -1) {
            outputView.showError("올바른 강의 번호를 입력해주세요.");
            return;
        }

        // 7. 조회수 증가 + 강의 상세 조회 및 출력
        contentDAO.increaseViewCount(lectureId);
        String[] lecture = contentDAO.getLectureDetail(lectureId);
        outputView.showLectureDetail(lecture);
    }

    // LEC-003 : 강의 상세 조회 + 조회수 증가
    /* comment.
        <역할>
        강의 번호 입력 -> 상세 정보 출력 + 조회수 자동 증가
        <흐름>
        1. 강의 번호 입력받기
        2. 입력값 유효성 검사 (-1이면 종료)
        3. 조회수 1씩 증가 (increaseViewCount)
        4. 강의 상세 정보 조회 및 출력
     */
    public void showLectureDetail() {
        // 1. 강의 번호 입력받기
        int lectureId = inputView.inputLectureId();

        // 2. 유효하지 않은 입력값 방어
        if (lectureId == -1) {
            outputView.showError("올바른 강의 번호를 입력해주세요.");
            return;
        }

        // 3. 조회수 먼저 증가
        contentDAO.increaseViewCount(lectureId);

        // 4. 강의 상세 조회 및 출력
        String[] lecture = contentDAO.getLectureDetail(lectureId);
        outputView.showLectureDetail(lecture);
    }

    // LEC-004 : 정렬 기준으로 강의 목록 조회
    /* comment.
        <역할>
        카테고리 선택 -> 정렬 기준 선택 -> 강의 목록 출력
        <흐름>
        1. 카테고리 목록 출력
        2. 카테고리 번호 입력받기
        3. 입력값 유효성 검사
        4. 카테고리 존재 여부 확인
        5. 정렬 옵션 입력받기 (1. 최신 / 2. 조회수 / 3. 좋아요)
        6. 정렬된 강의 목록 조회 및 출력
     */
    public void showLecturesSorted() {
        // 1. 카테고리 목록 먼저 보여주기
        showCategory();

        // 2. 카테고리 번호 입력받기
        int categoryId = inputView.inputCategoryId();

        // 3. 유효하지 않은 입력값 방어
        if (categoryId == -1) {
            outputView.showError("올바른 카테고리 번호를 입력해주세요.");
            return;
        }
        // 4. 카테고리 존재 여부 확인
        if (!contentDAO.categoryExists(categoryId)) {
            outputView.showError("존재하지 않는 카테고리입니다.");
            return;
        }

        // 5. 정렬 옵션 입력받기
        String sortType = inputView.inputSortOption();

        // 6. 정렬된 강의 목록 조회 및 출력
        List<String[]> lectures = contentDAO.getLecturesSorted(categoryId, sortType);
        outputView.showLectures(lectures);
    }

    // LEC-005 : 키워드 검색
    /* comment.
        <역할>
        키워드 입력 -> 정렬 기준 선택 -> 검색 결과 출력
        <흐름>
        1. 검색 키워드 입력받기
        2. Null 이면 Error 처리 후 종료
        3. 정렬 옵션 입력받기
        4. 키워드 + 정렬 기준으로 강의 검색 및 출력
     */
    public void searchLectures() {
        // 1. 키워드 입력받기
        String keyword = inputView.inputKeyword();

        // 2. null이면 입력 실패 처리
        if (keyword == null) {
            outputView.showError("검색 키워드를 입력해주세요.");
            return;
        }

        // 3. 정렬 옵션 입력받기
        String sortType = inputView.inputSortOption();

        // 4. 검색 결과 조회 및 출력
        List<String[]> lectures = contentDAO.searchLectures(keyword, sortType);
        outputView.showLectures(lectures);
    }

}
