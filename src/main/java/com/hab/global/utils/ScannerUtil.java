package com.hab.global.utils;

import java.util.Scanner;

public class ScannerUtil {

    /** <역할>
     *  프로그램 전체에서 Scanner 객체를 1개만 공유하기 위한 유틸 클래스
     *  Scanner 를 각 View 마다 따로 생성하면 System.in 입력 스트림 충돌로 입력이 씹히는 버그 발생
     *  이 클래스를 통해 getInstance() 로 동일한 Scanner 1개를 모든 View 에서 공유
     */

    private static final Scanner sc = new Scanner(System.in);

    public static Scanner getInstance() {
        return sc;
    }
}
