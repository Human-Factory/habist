package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Notice;
import com.hab.hobbymarket.service.AdminService;
import com.hab.hobbymarket.view.adminview.AdminOutputView;
import java.util.List;

public class AdminController {

    // 비즈니스 로직 처리 담당 (Service)
    private final AdminService adminService;

    // 화면 출력 담당 (OutputView)
    private final AdminOutputView outputView = new AdminOutputView();

    // 생성자에서 Service 주입
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 1. 회원 목록 조회
    public void showMembers() {

        // Service 호출 → DB에서 회원 목록 가져옴
        List<Member> list = adminService.getAllMembers();

        // 가져온 데이터를 OutputView로 전달해서 출력
        outputView.showMemberList(list);
    }

    // 2. 회원 상태 변경
    public void changeMemberStatus(long memberId, String status) {

        // Service에 상태 변경 요청
        boolean result = adminService.changeMemberStatus(memberId, status);

        // 결과에 따라 성공/실패 메시지 출력
        if (result) {
            System.out.println("회원 상태가 변경되었습니다.");
        } else {
            System.out.println("회원 상태 변경에 실패했습니다.");
        }
    }

    // 공지사항 목록 조회
    public void showNotices() {

        // 서비스 호출
        List<Notice> list = adminService.getAllNotices();

        // 출력 뷰로 전달
        outputView.showNoticeList(list);
    }

    // 3. 공지 등록
    public void createNotice(String title, String content) {

        // Service 호출 → 공지 등록
        boolean result = adminService.createNotice(title, content);

        if (result) {
            System.out.println("등록 완료");
        }else {
            System.out.println("등록 실패");
        }
    }

    // 4. 공지 수정
    public void editNotice(int noticeId, String title, String content) {

        boolean result = adminService.editNotice(noticeId, title, content);

        System.out.println(result ? "공지 수정 완료" : "공지 수정 실패");
    }

    // 5. 공지 삭제
    public void removeNotice(int noticeId) {

        boolean result = adminService.removeNotice(noticeId);

        System.out.println(result ? "공지 삭제 완료" : "공지 삭제 실패");
    }

    // 6. Q&A 등록
    public void createQna(String title, String content) {

        boolean result = adminService.createQna(title, content);

        System.out.println(result ? "Q&A 등록 완료" : "Q&A 등록 실패");
    }

    // ----------------------------------------------------
    // 7. Q&A 수정
    // ----------------------------------------------------
    public void editQna(int qnaId, String title, String content) {

        boolean result = adminService.editQna(qnaId, title, content);

        System.out.println(result ? "Q&A 수정 완료" : "Q&A 수정 실패");
    }

    // ----------------------------------------------------
    // 8. Q&A 삭제
    // ----------------------------------------------------
    public void removeQna(int qnaId) {

        boolean result = adminService.removeQna(qnaId);

        System.out.println(result ? "Q&A 삭제 완료" : "Q&A 삭제 실패");
    }
}