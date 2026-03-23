package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.CommentsDAO;
import com.hab.hobbymarket.view.commentsview.CommentsInputView;
import com.hab.hobbymarket.view.commentsview.CommentsOutputView;

import java.util.List;

/**
 * <역할>
 *     댓글 작성 / 조회 / 수정 / 삭제 흐름을 제어하는 컨트롤러
 *     패턴 : MVC + DAO 패턴
 * <흐름구조>
 *     사용자 입력
 *     CommentsInputView : 강의번호 / 댓글번호 / 댓글내용 입력
 *     CommentsController : 흐름제어 + 본인여부 검증
 *     CommentsDAO : DB 접근 (작성/조회/수정/삭제)
 *     CommentsOutputView : 결과 출력
 * <예외 처리 원칙>
 *     1. 강의/댓글 번호 오류 -> inputLectureId() / inputCommentId() 가 -1 반환 -> return
 *     2. 댓글 내용 오류 -> inputContent() 가 null 반환 -> return
 *     3. 타인 댓글 수정 시도 -> isCommentOwner() false 반환 -> showError()
 *     4. 타인 댓글 삭제 시도 -> isCommentOwner() false 반환 -> showError()
 */

public class CommentsController {

    private final CommentsDAO commentsDAO = new CommentsDAO();
    private final CommentsOutputView outputView = new CommentsOutputView();
    private final CommentsInputView inputView = new CommentsInputView();

    // CMT-001 : 댓글 작성

    /* comment.
        <역할>
         댓글 작성
        <흐름도>
        1. 강의 번호 입력받기 (-1 이라면 return)
        2. 댓글 내용 입력받기 (null 이라면 return)
        3. addComment() 호출 -> showCommentAdded()
     */

    public void addComment(int memberId) {
        // 1. 강의 번호 입력받기
        int lectureId = inputView.inputLectureId();
        if (lectureId == -1) {
            outputView.showError("올바른 강의 번호를 입력해주세요.");
            return;
        }

        // 2. 댓글 내용 입력받기
        String content = inputView.inputContent();
        if (content == null) {
            outputView.showError("올바른 댓글 내용을 입력해주세요.");
            return;
        }

        // 3. 댓글 작성 및 성공 출력
        commentsDAO.addComment(memberId, lectureId, content);
        outputView.showCommentAdded();
    }

    // CMT-002 : 댓글 목록 조회

    /* comment.
        <역할>
        특정 강의의 댓글 목록 조회 및 출력
        <흐름도>
        1. 강의 번호 입력받기 (-1 이면 return)
        2. getCommentsByLecture() 호출 -> showComments()
     */

    public void showComments() {
        // 1. 강의 번호 입력받기
        int lectureId = inputView.inputLectureId();
        if (lectureId == -1) {
            // 사용자가 올바르지 못한 값을 넣었을 경우 아래의 출력문 출력
            outputView.showError("올바른 강의 번호를 입력해주세요.");
            return;
        }

        // 2. 댓글 목록 조회 및 출력
        List<String[]> comments = commentsDAO.getCommentsByLecture(lectureId);
        outputView.showComments(comments);
    }

    // CMT-003 : 댓글 수정 (본인만)

    /* comment.
        <역할>
        댓글 수정 (본인만 가능)
        <흐름도>
        1. 댓글 번호 입력받기 (-1 이면 return)
        2. isCommentOwner() 본인 확인 (타인이면 return)
        3. 새 댓글 내용 입력 받기 (null 이면 return)
        4. updateComment() 호출 -> showCommentUpdated()
     */

    public void updateComment(int memberId) {
        // 1. 댓글 번호 입력받기
        int commentId = inputView.inputCommentId();
        if (commentId == -1) {
            outputView.showError("올바른 댓글 번호를 입력해주세요.");
            return;
        }

        // 2. 본인 여부 확인
        if (!commentsDAO.isCommentOwner(commentId, memberId)) {
            outputView.showError("본인이 작성한 댓글만 수정할 수 있습니다.");
            return;
        }

        // 3. 새 댓글 내용 입력받기
        String newContent = inputView.inputContent();
        if (newContent == null) {
            outputView.showError("올바른 댓글 내용을 입력해주세요.");
            return;
        }

        // 4. 수정 및 성공 출력
        commentsDAO.updateComment(commentId, newContent);
        outputView.showCommentUpdated();
    }

    // CMT-004 : 댓글 삭제 (본인만)

    /* comment.
        <역할>
        댓글 삭제 (본인만 가능)
        <흐름도>
        1. 댓글 번호 입력받기 (-1 이면 return)
        2. isCommentOwner() 본인 확인 (타인이면 return)
        3. deleteComment() 호출 -> showCommentDeleted()
     */

    public void deleteComment(int memberId) {
        // 1. 댓글 번호 입력받기
        int commentId = inputView.inputCommentId();
        if (commentId == -1) {
            outputView.showError("올바른 댓글 번호를 입력해주세요.");
            return;
        }

        // 2. 본인 여부 확인
        if (!commentsDAO.isCommentOwner(commentId, memberId)) {
            outputView.showError("본인이 작성한 댓글만 삭제할 수 있습니다.");
            return;
        }

        // 3. 삭제 및 성공 출력
        commentsDAO.deleteComment(commentId);
        outputView.showCommentDeleted();
    }
}