package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.ContentDAO;
import com.hab.hobbymarket.view.contentview.ContentInputView;
import com.hab.hobbymarket.view.contentview.ContentOutputView;

import java.util.List;

public class ContentController {

    // MVC + DAO 패턴 : Controller는 DAO(데이터)와 View(출력)를 연결하는 역할만 한다
    private final ContentDAO contentDAO = new ContentDAO();
    private final ContentOutputView outputView = new ContentOutputView();
    private final ContentInputView inputView = new ContentInputView();

    // LEC-001 : 카테고리 목록 조회
    public void showCategory() {
        List<String[]> categories = contentDAO.getCategories();
        outputView.showCategories(categories);
    }

    // LEC-002 : 카테고리별 강의 목록 조회
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
    }
}
