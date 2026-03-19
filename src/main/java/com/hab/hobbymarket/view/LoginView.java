package com.hab.hobbymarket.view;

import com.hab.hobbymarket.controller.LoginController;
import com.hab.hobbymarket.service.LoginResult;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.model.Member;

import java.util.Scanner;

public class LoginView {

    private final LoginController loginController = new LoginController();
    private final Scanner sc = new Scanner(System.in);

    public void login() {

        while (true) {
            System.out.println("===== 로그인 =====");

            System.out.print("아이디: ");
            String id = sc.nextLine();

            System.out.print("비밀번호: ");
            String pwd = sc.nextLine();

            LoginResult result = loginController.login(id, pwd);

            if (result == LoginResult.SUCCESS) {
                Member user = SessionManager.getCurrentUser();
                System.out.println(user.getNickname() + "님, 로그인 성공");
                break;
            }

            if (result == LoginResult.INVALID_CREDENTIAL) {
                System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
            } else if (result == LoginResult.LOCKED) {
                System.out.println("계정이 잠겨 있습니다. 잠시 후 다시 시도하세요.");
            } else if (result == LoginResult.INACTIVE) {
                System.out.println("비활성화된 계정입니다.");
            }
        }
    }

    public void logout() {
        loginController.logout();
    }
}