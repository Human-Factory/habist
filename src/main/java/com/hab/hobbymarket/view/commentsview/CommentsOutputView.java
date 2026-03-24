package com.hab.hobbymarket.view.commentsview;

import java.util.List;

public class CommentsOutputView {

    /**
     * <역할>
     *    댓글 기능의 처리 결과를 콘솔에 출력하는 View
     */

    // 댓글 목록 출력
    public void showComments(List<String[]> comments) {

        /* comment.
            <역할>
            댓글 목록 출력
            <흐름도>
            row[0] = comment_id
            row[1] = nickname
            row[2] = content
            row[3] = created_at
            row[4] = member_id (출력 안 함 — Controller 본인 여부 판단용)
         */

        System.out.println("\n=== 댓글 목록 ===");

        if (comments.isEmpty()) {
            System.out.println("등록된 댓글이 없습니다.");
            return;
        }

        // 아래의 주석에 맞게 내용을 String[]에 넣음
        for (String[] row : comments) {
            // row[0]=comment_id, row[1]=nickname, row[2]=content
            // row[3]=created_at, row[4]=member_id
            System.out.printf("[%s] %s | %s | %s\n",
                    row[0], row[1], row[2], row[3]);
        }
    }

    // 댓글 작성 성공 출력 (CommentsController 에서 addComment() 성공 후 호출
    public void showCommentAdded() {
        System.out.println("\n댓글이 등록되었습니다.");
    }

    // 댓글 수정 성공 출력 (commentsController 에서 updateComment() 성공 후 호출
    public void showCommentUpdated() {
        System.out.println("\n댓글이 수정되었습니다.");
    }

    // 댓글 삭제 성공 출력 (CommentsController 에서 deleteComment() 성공 후 호출
    public void showCommentDeleted() {
        System.out.println("\n댓글이 삭제되었습니다.");
    }

    // 에러 메시지 출력 (입력값 오류 / 타인 댓글 수정 삭제 시도 등 예외 상황에서 호출
    public void showError(String message) {
        System.out.println("[오류] " + message);
    }
}