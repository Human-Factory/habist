package com.hab.global.utils;

import java.util.Scanner;

public class ConsoleUtil {

    // 스캐너를 생성한다. 하지만 이 클래스에서 그리고 스캐너를 중간에 교체하지 않겠다 선언을 했다.
    private static final Scanner scanner = new Scanner(System.in);

    // 문자열 입력
    public static String readLine(String prompt) {
        System.out.print(prompt);

        // 사용자의 입력에 공백 발생 제거를 위해 trim() 사용
        return scanner.nextLine().trim();
    }

    // 숫자 입력
    public static int readInt(String prompt) {
        System.out.print(prompt);

        // 입력값이 숫자일 때 False
        while (!scanner.hasNextInt()) {
            System.out.print("숫자를 입력해주세요: ");

            // 단어 1개만 입력받기 위해 next구문 사용
            scanner.next();
        }
        int value = scanner.nextInt();

        // 다음 입력값이 넘어가는 것을 방지
        scanner.nextLine();
        return value;
    }

    // 메시지 출력
    public static void printMessage(String message) {

        // 줄바꿈을 위해서 \n 사용
        System.out.println("\n" + message + "\n");
    }

    // Y/N 확인 — 삭제 확인, 탈퇴 확인 등
    public static boolean confirmAction(String message) {
        System.out.print(message + " (Y/N): ");

        // 입력받은 값을 정렬하고, 대문자로 변경시키는 역할
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }
}
