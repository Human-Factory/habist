package com.hab.hobbymarket.view.contentview;

import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

public class ContentInputView {

    private final Scanner sc = ScannerUtil.getInstance();

    // 카테고리 ID 입력받기
    public int inputCategoryId() {
        System.out.print("\n조회할 카테고리 번호를 입력하세요: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1; // 유효하지 않은 값 반환
        }
    }

    // 강의 ID 입력받기
    /* comment.
        inputLectureId() -> 기존의 inputCategoryId() 랑 완전히 동일한 구조방식
        강의 ID 를 입력받았을 시 조회할 수 있게 진행
     */

    public int inputLectureId() {
        System.out.print("\n조회할 강의 번호를 입력하세요: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }

    // 정렬 옵션 입력받기
    /* comment.
        정렬 옵션을 다음과 같이 설정했다.
        1. 최신순
        2. 조회수순
        3. 좋아요순
        만약 1 ~ 3 안의 숫자가 아닌 다른 숫자가 입력되었을 시 1,2,3 중에서 입력하라고 출력문 출력
     */
    public String inputSortOption() {
        System.out.println("\n정렬 방식을 선택하세요.");
        System.out.println("1. 최신순  2. 조회수순  3. 좋아요순");
        System.out.print("선택: ");
        String input = sc.nextLine().trim();
        if (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("1, 2, 3 중에서 입력해주세요.");
            return "1";
        }
        return input;
    }

    // 검색 키워드 입력받기
    /* comment.
        1. 스캐너 객체를 통해 검색할 키워드 입력받기
        2. 공백으로 검색했을 경우 키워드를 입력하라고 출력문 출력
     */
    public String inputKeyword() {
        System.out.print("\n검색할 키워드를 입력하세요: ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("키워드를 입력해주세요.");
            return null;
        }
        return input;
    }


}
