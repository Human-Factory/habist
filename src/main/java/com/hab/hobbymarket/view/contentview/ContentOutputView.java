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

    // 강의 상세 정보 출력

    /* comment. <역할>
        1. DAO 의 getLectureDetail() 반환값을 그대로 받는 역할
        2. null 값 체크하기 / 존재하지 않는 강의 ID가 생겼을 시 기존 showError() 재사용
        3. lecture 0~6 순서는 DAO에서 잡아놓은 배열 인덱스와 1 대 1 매치
     */

    public void showLectureDetail(String[] lecture) {
        // 만약 lecture 에 아무것도 없다면
        if (lecture == null) {
            showError("존재하지 않는 강의입니다.");
            return;
        }

        // 강의 상세 정보 출력해서 유저에게 보여주기
        System.out.println("\n=== 강의 상세 정보 ===");
        System.out.printf("강의 번호  : %s\n", lecture[0]);
        System.out.printf("제    목  : %s\n", lecture[1]);
        System.out.printf("카테고리  : %s\n", lecture[6]);
        System.out.printf("강    사  : %s\n", lecture[3]);
        System.out.printf("설    명  : %s\n", lecture[2]);
        System.out.printf("조 회 수  : %s\n", lecture[4]);
        System.out.printf("좋아요수  : %s\n", lecture[5]);
    }
}
