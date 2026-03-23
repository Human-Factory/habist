package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.NoticeDAO;
import com.hab.hobbymarket.view.noticeview.NoticeInputView;
import com.hab.hobbymarket.view.noticeview.NoticeOutputView;

import java.util.List;

/**
 * <역할>
 *     공지사항 등록/목록/상세/수정/삭제 흐름을 제어하는 컨트롤러
 * <패턴>
 *     MVC + DAO 패턴
 * <흐름 구조>
 *     사용자 입력
 *     NoticeInputView : 번호 / 제목 / 내용 입력
 *     NoticeController : 흐름 제어
 *     NoticeDAO : DB 접근
 *     NoticeOutputView : 결과 출력
 * <예외 처리 원칙>
 *     1. 번호 입력 오류 : inputNoticeId() 가 -1 반환 시 return
 *     2. 제목 입력 오류 : inputTitle() 가 null 반환 시 return
 *     3. 내용 입력 오류 : inputContent() 가 null 반환 시 return
 */

public class NoticeController {

    private final NoticeDAO noticeDAO = new NoticeDAO();
    private final NoticeOutputView outputView = new NoticeOutputView();
    private final NoticeInputView inputView = new NoticeInputView();

    // NTC-001 : 공지사항 등록

    /* comment.
        <역할>
        공지사항 등록
        <흐름도>
        1. 제목 입력 받기 (null 일 경우 return)
        2. 내용 입력 받기 (null 일 경우 return)
        3. addNotice() -> showNoticeAdded()
     */

    public void addNotice(int authorId) {
        // 1. 제목 입력받기
        String title = inputView.inputTitle();
        // title 이 null 일 경우 아래의 출력문을 출력
        if (title == null) {
            outputView.showError("올바른 제목을 입력해주세요.");
            return;
        }

        // 2. 내용 입력받기
        String content = inputView.inputContent();
        // content 가 null 일 겨우 아래의 출력문 출력
        if (content == null) {
            outputView.showError("올바른 내용을 입력해주세요.");
            return;
        }

        // 3. 등록 및 성공 출력
        noticeDAO.addNotice(authorId, title, content);
        outputView.showNoticeAdded();
    }

    // NTC-002 : 공지사항 목록 조회

    /* comment.
        <역할>
        공지사항 전체 목록 조회 및 출력
        <흐름도>
        getNotices() -> showNotices()
     */

    public void showNotices() {
        // 1. 목록 조회 및 출력
        List<String[]> notices = noticeDAO.getNotices();
        outputView.showNotices(notices);
    }

    /* comment.
        <역할>
        공지사항 상세 조회
        <흐름도>
        1. 목록 출력 (showNotices 재사용)
        2. 번호 입력받기 (-1 이면 return)
        3. increaseViewCount() 조회수 먼저 증가
        4. getNoticeDetail() -> showNoticeDetail()
     */

    // NTC-003 : 공지사항 상세 조회
    public void showNoticeDetail() {
        // 1. 목록 먼저 보여주기
        showNotices();

        // 2. 공지사항 번호 입력받기
        int noticeId = inputView.inputNoticeId();
        // 옳지 않은 값을 넣었을 경우 아래의 출력문을 출력
        if (noticeId == -1) {
            outputView.showError("올바른 공지사항 번호를 입력해주세요.");
            return;
        }

        // 3. 조회수 먼저 증가
        noticeDAO.increaseViewCount(noticeId);

        // 4. 상세 조회 및 출력
        String[] notice = noticeDAO.getNoticeDetail(noticeId);
        outputView.showNoticeDetail(notice);
    }

    // NTC-004 : 공지사항 수정

    /* comment.
        <역할>
        공지사항 수정
        <흐름도>
        1. 목록 출력 ( showNotices 재사용 )
        2. 번호 입력받기 (-1 이라면 return)
        3. 새 제목 입력받기 (null 이면 return)
        4. 새 내용 입력받기 (null 이면 return)
        5. updateNotice() -> showNoticeUpdated()
     */

    public void updateNotice() {
        // 1. 목록 먼저 보여주기
        showNotices();

        // 2. 공지사항 번호 입력받기
        int noticeId = inputView.inputNoticeId();
        if (noticeId == -1) {
            outputView.showError("올바른 공지사항 번호를 입력해주세요.");
            return;
        }

        // 3. 새 제목 입력받기
        String newTitle = inputView.inputTitle();
        if (newTitle == null) {
            outputView.showError("올바른 제목을 입력해주세요.");
            return;
        }

        // 4. 새 내용 입력받기
        String newContent = inputView.inputContent();
        if (newContent == null) {
            outputView.showError("올바른 내용을 입력해주세요.");
            return;
        }

        // 5. 수정 및 성공 출력
        noticeDAO.updateNotice(noticeId, newTitle, newContent);
        outputView.showNoticeUpdated();
    }

    // NTC-005 : 공지사항 삭제

    /* comment.
        <역할>
        공지사항 삭제
        <흐름도>
        1. 목록 출력 (showNotices 재사용)
        2. 번호 입력받기 (-1 이면 return)
        3. deleteNotice() -> showNoticeDeleted()
     */

    public void deleteNotice() {
        // 1. 목록 먼저 보여주기
        showNotices();

        // 2. 공지사항 번호 입력받기
        int noticeId = inputView.inputNoticeId();
        if (noticeId == -1) {
            outputView.showError("올바른 공지사항 번호를 입력해주세요.");
            return;
        }

        // 3. 삭제 및 성공 출력
        noticeDAO.deleteNotice(noticeId);
        outputView.showNoticeDeleted();
    }
}
