package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.LikesDAO;
import com.hab.hobbymarket.view.likesview.LikesInputView;
import com.hab.hobbymarket.view.likesview.LikesOutputView;

public class LikesController {

    /**
     * <역할>
     *     좋아요 등록/취소 흐름을 제어하는 컨트롤러
     * <흐름도>
     *     사용자 입력
     *     LikesInputView : 강의 번호 / 등록.취소 선택 입력
     *     LikesController : 흐름 제어 + 중복/존재 여부 검증
     *     LikesDAO : DB 접근 (등록/취소/중복확인)
     *     LikesOutputView : 결과 출력
     * <예외 처리 원칙>
     *     1. 강의 번호 입력 오류 -> inputLectureId() 가 -1 반환 -> return
     *     2. 선택값 입력 오류 -> inputLikeAction() 이 "-1" 반환 -> return
     *     3. 중복 좋아요 시도 -> alreadyLiked() 로 사전 체크 -> showError()
     *     4. 좋아요 없는 취소 -> alreadyLiked() 로 사전 체크 -> showError()
     */

    private final LikesDAO likesDAO = new LikesDAO();
    private final LikesOutputView outputView = new LikesOutputView();
    private final LikesInputView inputView = new LikesInputView();

    // LKE-001 : 좋아요 등록 / 취소

    /* comment.
        <역할>
        좋아요 등록 / 취소 전체 흐름 제어
        <흐름도>
        1. 강의 번호 입력받기
        2. 입력값 유효성 검사 (-1 이면 return)
        3. 등록(1) / 취소(2) 선택받기
        4. 선택값 유효성 검사 ("-1" 이면 return)
        5-1. 등록 선택 시 -> alreadyLiked() 중복 확인 -> addLike() -> showLikeAdded()
        5-2. 취소 선택 시 -> alreadyLiked() 존재 확인 -> removeLike() -> showLikeRemoved()
     */

    public void handleLike(int memberId) {
        // 1. 강의 번호 입력받기
        int lectureId = inputView.inputLectureId();

        // 2. 유효하지 않은 입력값 방어
        if (lectureId == -1) {
            outputView.showError("올바른 강의 번호를 입력해주세요.");
            return;
        }

        // 3. 등록 / 취소 선택받기
        String action = inputView.inputLikeAction();

        // 4. 유효하지 않은 선택값 방어
        if (action.equals("-1")) {
            outputView.showError("올바른 선택지를 입력해주세요.");
            return;
        }

        // 5. 등록(1) / 취소(2) 분기 처리
        if (action.equals("1")) {
            // 중복 좋아요 방지
            if (likesDAO.alreadyLiked(memberId, lectureId)) {
                outputView.showError("이미 좋아요를 누른 강의입니다.");
                return;
            }
            likesDAO.addLike(memberId, lectureId);
            outputView.showLikeAdded();

        } else {
            // 좋아요 없는데 취소 방지
            if (!likesDAO.alreadyLiked(memberId, lectureId)) {
                outputView.showError("좋아요를 누르지 않은 강의입니다.");
                return;
            }
            likesDAO.removeLike(memberId, lectureId);
            outputView.showLikeRemoved();
        }
    }
}