package com.hab.hobbymarket.view.commentsview;

import java.util.Scanner;

/** <역할>
 *      댓글 기능에 필요한 사용자 입력을 수신하는 View
 */

public class CommentsInputView {

    /* comment.
        <역할>
        댓글 내용 입력받기
        <흐름도>
        Controller 에서 null 체크 후 addComment() / updateComment() 호출
     */

    // Scanner 객체 사용 (하지만 private final을 통해서 변경 못하게 진행)
    private final Scanner sc = new Scanner(System.in);

    // 댓글 내용 입력받기
    public String inputContent() {
        System.out.print("\n댓글 내용을 입력하세요 (최대 500자): ");
        String input = sc.nextLine().trim();
        // 만약 댓글 내용이 null 값이라면 아래의 출력문을 출력
        if (input.isEmpty()) {
            System.out.println("댓글 내용을 입력해주세요.");
            return null;
        }
        // 만약 댓글이 500자 이상이라면 아래의 출력문을 출력
        if (input.length() > 500) {
            System.out.println("댓글은 500자 이내로 입력해주세요.");
            return null;
        }
        return input;
    }

    // 댓글 번호 입력받기
    public int inputCommentId() {
        System.out.print("\n댓글 번호를 입력하세요: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
            // 만약 댓글 번호가 아닌 문자를 입력했을 경우 아래의 Error 출력
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }

    // 강의 번호 입력받기
    public int inputLectureId() {
        System.out.print("\n강의 번호를 입력하세요: ");
        try {
            // Scanner 객체로 입력받기
            return Integer.parseInt(sc.nextLine().trim());
            // 만약 숫자가 아닌 문자열을 입력했을 시 Error 출력
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }
}