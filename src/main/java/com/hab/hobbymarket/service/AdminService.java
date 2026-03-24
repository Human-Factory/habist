package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.AdminDAO;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Notice;

import java.util.List;

public class AdminService {

    // 실제 DB 접근은 DAO가 하므로, Service는 DAO를 가지고 있어야 함
    private final AdminDAO adminDAO;

    // 생성자를 통해 AdminDAO를 주입받음
    // Application.java에서 AdminDAO를 만들고 여기로 넘겨줌
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    // 1. 전체 회원 조회
    public List<Member> getAllMembers() {

        // 회원 전체 조회는 DB에서 가져와야 하므로
        // DAO의 findAllMembers() 메서드를 호출
        return adminDAO.findAllMembers();
    }

    // 2. 회원 상태 변경
    // 예: 경고, 정지, 정지해제
    public boolean changeMemberStatus(long memberId, String status) {

        // memberId: 상태를 바꿀 회원 번호
        // status: 변경할 상태값
        // 실제 UPDATE 문 실행은 DAO에게 맡김
        return adminDAO.updateMemberStatus(memberId, status);
    }

    // 공지사항 전체 조회
    public List<Notice> getAllNotices() {
        return adminDAO.findAllNotices();
    }

    // 3. 공지사항 등록

    public boolean createNotice(String title, String content) {

        // title: 공지 제목
        // content: 공지 내용
        // DB insert는 DAO에서 처리
        return adminDAO.insertNotice(title, content);
    }

    // 4. 공지사항 수정
    public boolean editNotice(int noticeId, String title, String content) {

        // noticeId: 수정할 공지 번호
        // title: 새 제목
        // content: 새 내용
        // 실제 UPDATE는 DAO가 수행
        return adminDAO.updateNotice(noticeId, title, content);
    }

    // 5. 공지사항 삭제
    public boolean removeNotice(int noticeId) {

        // noticeId에 해당하는 공지 삭제
        return adminDAO.deleteNotice(noticeId);
    }

    // 6. Q&A 등록
    public boolean createQna(String title, String content) {

        // Q&A 제목과 내용을 받아서 DAO에 전달
        return adminDAO.insertQna(title, content);
    }

    // 7. Q&A 수정
    public boolean editQna(int qnaId, String title, String content) {

        // qnaId: 수정할 Q&A 번호
        // title: 새 제목
        // content: 새 내용
        return adminDAO.updateQna(qnaId, title, content);
    }

    // 8. Q&A 삭제
    public boolean removeQna(int qnaId) {

        // qnaId에 해당하는 Q&A 삭제
        return adminDAO.deleteQna(qnaId);
    }
}