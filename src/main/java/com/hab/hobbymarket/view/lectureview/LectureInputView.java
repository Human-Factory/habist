package com.hab.hobbymarket.view.lectureview;

import com.hab.hobbymarket.controller.LectureController;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.LectureUpdateRequest;

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
     */
    public void createLecture() {
        LectureCreateRequest request = getLectureCreateInput();
        lectureController.createLecture(request);
    }

    /**
     * 강의 등록 입력값 받기
     */
    public LectureCreateRequest getLectureCreateInput() {

        System.out.println("===== 강의 등록 =====");

        System.out.print("카테고리 번호 입력: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        System.out.print("강의 제목 입력: ");
        String title = scanner.nextLine();

        System.out.print("강의 설명 입력: ");
        String description = scanner.nextLine();

        return new LectureCreateRequest(categoryId, title, description);
    }

    /**
     * 강의 수정 전체 흐름 실행
     */
    public void updateLecture() {
        LectureUpdateRequest request = getLectureUpdateInput();
        lectureController.updateLecture(request);
    }

    /**
     * 강의 수정 입력값 받기
     */
    public LectureUpdateRequest getLectureUpdateInput() {

        System.out.println("===== 강의 수정 =====");

        // 수정할 강의 번호 입력
        System.out.print("수정할 강의 번호 입력: ");
        int lectureId = Integer.parseInt(scanner.nextLine());

        // 새 카테고리 번호 입력
        System.out.print("새 카테고리 번호 입력: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        // 새 강의 제목 입력
        System.out.print("새 강의 제목 입력: ");
        String title = scanner.nextLine();

        // 새 강의 설명 입력
        System.out.print("새 강의 설명 입력: ");
        String description = scanner.nextLine();

        return new LectureUpdateRequest(lectureId, categoryId, title, description);
    }

    /**
     * 강의 삭제 전체 흐름 실행
     */
    public void deleteLecture() {
        int lectureId = getLectureDeleteInput();
        lectureController.deleteLecture(lectureId);
    }

    /**
     * 강의 삭제용 강의 번호 입력
     */
    public int getLectureDeleteInput() {

        System.out.println("===== 강의 삭제 =====");
        System.out.print("삭제할 강의 번호 입력: ");

        return Integer.parseInt(scanner.nextLine());
    }
}