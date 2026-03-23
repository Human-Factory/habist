package com.hab.hobbymarket.view.noticeview;

import java.util.Scanner;

/** <역할>
 *      공지사항 기능에 필요한 사용자 입력을 수신하는 View
 */


public class NoticeInputView {

    /* comment.
        <역할>
            조회/수정/삭제할 공지사항 번호 입력받기
        <용도>
            Controller 에서 -1 체크 후 즉시 return 처리
     */

    // Scanner 객체 사용
    private final Scanner sc = new Scanner(System.in);

    // 공지사항 번호 입력받기
    public int inputNoticeId() {
        System.out.print("\n공지사항 번호를 입력하세요: ");
        // try-catch 구문으로 예외처리
        try {
            return Integer.parseInt(sc.nextLine().trim());
            // Error 발생 시 문제 출력
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
            // View 클래스기 때문에 DB 를 건드리지 않아서 사용할 필요가 없음
            // DAO 에서는 필요하지만, 일반 View 클래스에서는 닫을 자원이 없음
        }
    }

    // 공지사항 제목 입력받기

    /* comment.
        <역할>
        공지사항 제목 입력 받기
        <검증방식>
        1. 빈값 입력 시 null 반환
        2. 100 자 초과 입력 시 null 반환 ( notices 테이블 varchar(100) 제한 )
     */

    public String inputTitle() {
        System.out.print("\n제목을 입력하세요 (최대 100자): ");
        // Scanner 객체를 사용해서 입력하기
        String input = sc.nextLine().trim();
        // 만약 null 이라면 아래의 출력문을 반환한다.
        if (input.isEmpty()) {
            System.out.println("제목을 입력해주세요.");
            return null;
        }
        // 만약 100줄 이상이라면 아래의 출력문을 반환한다.
        if (input.length() > 100) {
            System.out.println("제목은 100자 이내로 입력해주세요.");
            return null;
        }
        return input;
    }

    // 공지사항 내용 입력받기

    /* comment.
        <역할>
        공지사항 내용 입력받기
        <흐름도>
        content 는 text 타입이라 길이 제한 없음 - null 값만 방어
    */
    public String inputContent() {
        System.out.print("\n내용을 입력하세요: ");
        // Scanner 객체로 입력 받기
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("내용을 입력해주세요.");
            return null;
        }
        return input;
    }
}