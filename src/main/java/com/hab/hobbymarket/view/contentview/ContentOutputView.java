package com.hab.hobbymarket.view.contentview;

import java.util.List;

public class ContentOutputView {

    // 카테고리 목록 출력
    public void showCategories(List<String[]> categories) {
        System.out.println("\n=== 카테고리 목록 ===");

        if (categories.isEmpty()) {
            System.out.println("등록된 카테고리가 없습니다.");
            return;
        }

        for (String[] row : categories) {
            // row[0] = category_id, row[1] = category_name, row[2] = lecture_count
            System.out.printf("%s. %s (%s개 강의)\n", row[0], row[1], row[2]);
        }
    }

    // 강의 목록 출력
    public void showLectures(List<String[]> lectures) {
        System.out.println("\n=== 강의 목록 ===");

        if (lectures.isEmpty()) {
            System.out.println("해당 카테고리에 강의가 없습니다.");
            return;
        }

        for (String[] row : lectures) {
            // row[0] = lecture_id, row[1] = title, row[2] = instructor_name
            // row[3] = view_count, row[4] = like_count
            System.out.printf("[%s] %s | 강사: %s | 조회수: %s | 좋아요: %s\n",
                    row[0], row[1], row[2], row[3], row[4]);
        }
    }

    // 에러 메세지 출력
    public void showError(String message) {
        System.out.println("[오류] " + message);
    }
}
