package com.hab.hobbymarket.view.noticeview;

import java.util.List;

/** <역할>
 *      공지사항 기능의 처리 결과를 콘솔에 출력하는 View
 */


public class NoticeOutputView {

    // 공지사항 목록 출력

    /* comment.
        <역할>
        공지사항 전체 목록 출력
        <반환 배열 구조>
            row[0] = notice_id
            row[1] = author_id  (목록에서 출력 안 함)
            row[2] = title
            row[3] = view_count
            row[4] = created_at
     */

    public void showNotices(List<String[]> notices) {
        System.out.println("\n=== 공지사항 목록 ===");
        // 만약 notices 가 null 이라면 아래의 출력문을 출력
        if (notices.isEmpty()) {
            System.out.println("등록된 공지사항이 없습니다.");
            return;
        }

        for (String[] row : notices) {
            // row[0]=notice_id, row[1]=author_id, row[2]=title
            // row[3]=view_count, row[4]=created_at
            System.out.printf("[%s] %s | 조회수: %s | %s\n",
                    row[0], row[2], row[3], row[4]);
        }
    }

    // 공지사항 상세 출력

    /* comment.
        <역할>
        공지 사항 상세 정보 출력
        <반환 배열 구조>
        notice[0] = notice_id
        notice[1] = author_id  (출력 안 함)
        notice[2] = title
        notice[3] = content    ← 상세에서만 표시
        notice[4] = view_count
        notice[5] = created_at
     */

    public void showNoticeDetail(String[] notice) {
        // 만약 notice가 null 이라면 아래의 출력문을 출력
        if (notice == null) {
            showError("존재하지 않는 공지사항입니다.");
            return;
        }

        // 반환 배열 구조에 맞춰서 sout 구문 출력
        System.out.println("\n=== 공지사항 상세 ===");
        System.out.printf("번호    : %s\n", notice[0]);
        System.out.printf("제목    : %s\n", notice[2]);
        System.out.printf("내용    : %s\n", notice[3]);
        System.out.printf("조회수  : %s\n", notice[4]);
        System.out.printf("작성일  : %s\n", notice[5]);
    }

    // 공지사항 등록 성공 출력
    public void showNoticeAdded() {
        System.out.println("\n공지사항이 등록되었습니다.");
    }

    // 공지사항 수정 성공 출력
    public void showNoticeUpdated() {
        System.out.println("\n공지사항이 수정되었습니다.");
    }

    // 공지사항 삭제 성공 출력
    public void showNoticeDeleted() {
        System.out.println("\n공지사항이 삭제되었습니다.");
    }

    // 에러 메시지 출력
    public void showError(String message) {
        System.out.println("[오류] " + message);
    }
}
