package com.hobbymarket.domain;

public class InputValidator {

    /* comment.
        InputValidator 의 역할이 하는 것은 다음과 같다.
        1. 댓글, 검색어 등 null 값이 있는지 확인하는 역할
        2. 특수문자만 입력했는지 확인하는 역할
        3. 제한된 글자 수를 초과했는지 확인하는 역할
        4. 사용자가 원하는 정렬 옵션을 제공한다.
     */

    // 빈값 체크 — 댓글, 검색어, 공지 제목 등에서 사용
    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    // 특수문자만 입력했는지 — 키워드 검색에서 사용
    public static boolean isSpecialCharOnly(String input) {

        /* 정규표현식 작성
            ^ : 문자열의 시작 [대괄호 안에 ^가 있을 경우 제외한다라는 뜻이다.]
            해석 : 문자열의 처음부터 끝까지 영문, 숫자, 한글이 하나도 없으며,
            특수문자만 이루어져있는가?
         */
        return input.matches("^[^a-zA-Z0-9가-힣]+$");
    }

    // 글자수 초과 체크 — 댓글 500자, 공지 제목 100자 등
    public static boolean isOverLength(String input, int maxLength) {
        return input.length() > maxLength;
    }

    // 정렬 옵션 유효성 — 1:최신순 2:조회수순 3:좋아요순
    public static boolean isValidSortOption(String option) {
        return option.equals("1") || option.equals("2") || option.equals("3");
    }
}