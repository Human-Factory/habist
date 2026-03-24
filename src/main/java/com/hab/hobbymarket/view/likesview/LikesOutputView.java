package com.hab.hobbymarket.view.likesview;

public class LikesOutputView {

    /**
     * <역할>
     *     좋아요 기능의 처리 결과를 콘솔에 출력하는 View
     */


    // 좋아요 등록 성공 출력

    /* comment.
        <역할>
        좋아요 등록 성공 메세지 출력
        <흐름도>
        LikesController.addLike() 성공 후 호출
     */

    public void showLikeAdded() {
        System.out.println("\n좋아요가 등록되었습니다.");
    }

    // 좋아요 취소 성공 출력

    /* comment.
        <역할>
        좋아요 취소 성공 메세지 추력
        <흐름도>
        LikesController.removeLike() 성공 후 호출
     */

    public void showLikeRemoved() {
        System.out.println("\n좋아요가 취소되었습니다.");
    }

    // 에러 메시지 출력

    /* comment.
        <역할>
        에러 메세지 출력
        <흐름도>
        중복 좋아요 시도 / 또는 좋아요 없는데 취소 시도 등 예외 상황에서 호출
     */

    public void showError(String message) {
        System.out.println("[오류] " + message);
    }
}