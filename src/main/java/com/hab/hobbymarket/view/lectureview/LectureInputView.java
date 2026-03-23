package com.hab.hobbymarket.view.lectureview;

import com.hab.hobbymarket.controller.LectureController;
import com.hab.hobbymarket.model.LectureCreateRequest;

import java.util.Scanner;

public class LectureInputView {

    // 강의 관련 요청을 처리할 Controller
    private final LectureController lectureController;

    // 입력용 Scanner
    private final Scanner scanner = new Scanner(System.in);

    public LectureInputView(LectureController lectureController) {
        this.lectureController = lectureController;
    }

    /**
     * 강의 등록 전체 흐름 실행
     *
     * 1. 사용자에게 카테고리/제목/설명을 입력받고
     * 2. LectureCreateRequest 객체를 만든 뒤
     * 3. Controller에 전달한다.
     */
    public void createLecture() {
        LectureCreateRequest request = getLectureCreateInput();
        lectureController.createLecture(request);
    }

    public LectureCreateRequest getLectureCreateInput() {

        System.out.println("===== 강의 등록 =====");

        System.out.print("카테고리 번호 입력: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        // 강의 제목 입력
        System.out.print("강의 제목 입력: ");
        String title = scanner.nextLine();

        // 강의 설명 입력
        System.out.print("강의 설명 입력: ");
        String description = scanner.nextLine();

        // 입력받은 값들을 DTO로 묶어서 반환
        return new LectureCreateRequest(categoryId, title, description);
    }
}