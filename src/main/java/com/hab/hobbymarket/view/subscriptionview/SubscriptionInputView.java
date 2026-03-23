package com.hab.hobbymarket.view.subscriptionview;

import com.hab.hobbymarket.controller.SubscriptionController;

import java.util.Scanner;

public class SubscriptionInputView {

        // 컨트롤러 객체
        private final SubscriptionController subscriptionController;

        // 입력용 Scanner
        private final Scanner sc = new Scanner(System.in);

    public SubscriptionInputView(SubscriptionController subscriptionController) {
            this.subscriptionController = subscriptionController;
        }

        // 구독 메뉴
        public void showSubscriptionMenu() {
            while (true) {
                System.out.println("\n========== 구독 메뉴 ==========");
                System.out.println("1. 구독하기");
                System.out.println("2. 내 구독 조회");
                System.out.println("3. 구독 취소");
                System.out.println("0. 뒤로가기");
                System.out.print("선택 : ");

                String menu = sc.nextLine().trim();

                switch (menu) {
                    case "1" -> subscribeFlow();
                    case "2" -> subscriptionController.showMySubscription();
                    case "3" -> cancelFlow();
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("올바른 번호를 입력해주세요.");
                }
            }
        }

        // 구독하기 흐름
        // "한다 / 안 한다" 선택지 구현
        private void subscribeFlow() {

            System.out.println("구독을 하시겠습니까?");
            System.out.println("1. 한다");
            System.out.println("2. 안 한다");
            System.out.print("선택 : ");

            String answer = sc.nextLine().trim();

            if ("1".equals(answer)) {
                subscriptionController.subscribeApp();
            } else if ("2".equals(answer)) {
                System.out.println("구독이 취소되었습니다.");
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }

        // 구독 취소 흐름
        private void cancelFlow() {

            System.out.println("정말 구독을 취소하시겠습니까?");
            System.out.println("1. 취소한다");
            System.out.println("2. 유지한다");
            System.out.print("선택 : ");

            String answer = sc.nextLine().trim();

            if ("1".equals(answer)) {
                subscriptionController.cancelMySubscription();
            } else if ("2".equals(answer)) {
                System.out.println("구독을 유지합니다.");
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
}

