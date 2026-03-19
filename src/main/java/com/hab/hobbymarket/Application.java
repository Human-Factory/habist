package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.ContentController;

public class Application {
    public static void main(String[] args) {

        ContentController controller = new ContentController();

        // LEC-001 테스트 : 카테고리 목록 조회
        controller.showCategory();

        // LEC-002 테스트 : 카테고리별 강의 목록 조회 (사용자 입력 포함)
        controller.showLecturesByCategory();
    }
}