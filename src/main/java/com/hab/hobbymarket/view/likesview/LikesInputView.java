package com.hab.hobbymarket.view.likesview;

import java.util.Scanner;
import com.hab.global.utils.ScannerUtil;

/**
 * <역할>
 *     좋아요 기능에 필요ㅕ한 사용자 입력을 수신하는 View
 */


public class LikesInputView {

    /* comment.
        <역할>
        좋아요 등록 / 취소할 강의 번호 입력받기
        <흐름>
        Controller 에서 가장 먼저 호출 : -1 이면 즉시 return 처리
     */

        private final Scanner sc = ScannerUtil.getInstance();

        // 좋아요 등록/취소할 강의 번호 입력받기
        public int inputLectureId() {
            System.out.print("\n강의 번호를 입력하세요: ");
            // try-catch 구문으로 예외처리
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
                return -1;
            }
        }

        // 좋아요 등록(1) / 취소(2) 선택받기
        public String inputLikeAction() {

            /* comment.
                <역할>
                좋아요 등록 (1) / 취소 (2) 선택하기
                <흐름도>
                Controller 에서 반환값에 따라 addLike() / removeLike() 분기 처리하기
             */

            System.out.println("\n1. 좋아요 등록  2. 좋아요 취소");
            System.out.print("선택: ");
            // Scanner를 통해서 받아오기
            String input = sc.nextLine().trim();
            // 만약 1 또는 2가 아니라면 출력문 작성
            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("1 또는 2를 입력해주세요.");
                return "-1";
            }
            return input;
        }
    }


