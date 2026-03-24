package com.hab.hobbymarket.view.adminview;

// 회원 모델 import
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Notice;

import java.util.List;

// 출력 전용 클래스
public class AdminOutputView {

    // ----------------------------------------------------
    // 회원 목록 출력
    // ----------------------------------------------------
    public void showMemberList(List<Member> list) {

        System.out.println("\n========== 회원 목록 ==========");

        // 데이터 없을 경우
        if (list == null || list.isEmpty()) {
            System.out.println("회원이 없습니다.");
            return;
        }

        // 회원 정보 출력
        for (Member member : list) {

            System.out.println("회원번호 : " + member.getMemberId());
            System.out.println("아이디 : " + member.getLoginId());
            System.out.println("닉네임 : " + member.getNickname());
            System.out.println("역할 : " + member.getRole());
            System.out.println("상태 : " + member.getStatus());

            System.out.println("--------------------------------");
        }
    }

    // 공지사항 목록 출력
    public static void showNoticeList(List<Notice> list) {

        System.out.println("\n========== 공지사항 목록 ==========");

        // 공지가 없으면 안내 문구 출력
        if (list == null || list.isEmpty()) {
            System.out.println("등록된 공지사항이 없습니다.");
            return;
        }

        // 공지 하나씩 출력
        for (Notice notice : list) {
            System.out.println("공지번호 : " + notice.getNoticeId());
            System.out.println("제목 : " + notice.getTitle());
            System.out.println("내용 : " + notice.getContent());
            System.out.println("등록일 : " + notice.getCreatedAt());
            System.out.println("--------------------------------");
        }
    }
}
