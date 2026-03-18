package com.hab;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Register rgst = new Register();

        Scanner sc = new Scanner(System.in);
        System.out.println("안녕하세요 하비스입니다.");
        System.out.println("1.로그인");
        System.out.println("2.회원가입");
        int no = sc.nextInt();
        if (no == 1) {
            while (true) {


                System.out.println("로그인을 해주십시오");
                System.out.println("아이디를 입력해주십시오 :");
                String id= sc.nextLine();
                System.out.println("비밀번호를 입력해주십시오 :");
                String pwd = sc.nextLine();


                if (logIn(id,pwd) = false) {
                    System.out.println("아이디와 비밀번호를 확인해주십시오");
                    return;
                } else {
                    break;
                }
            }
        } else if (no == 2) {
            System.out.println("회원가입");

            while (true) {
                System.out.println("이름을 입력해주십시오 :");
                String name = sc.nextLine();
                if (rgst.registerName(name) = true) {
                    break;
                } else {
                    System.out.println("이름을 다시 입력하여 주십시오");
                    return;
                }
            }

            while (true) {
                System.out.println("아이디를 입력해주십시오 :");
                String id = sc.nextLine();
                if (rgst.registerId(id) = true) {
                    break;
                } else {
                    System.out.println("아이디를 다시 입력하여 주십시오");
                    return;
                }
            }

            while (true) {
                System.out.println("비밀번호를 입력해주십시오 :");
                String pwd = sc.nextLine();
                if (rgst.registerPwd(pwd) = true) {
                    break;
                } else {
                    System.out.println("비밀번호를 다시 입력하여 주십시오");
                    return;
                }
            }

            while (true) {
                System.out.println("닉네임을 입력해주십시오 :");
                String nick = sc.nextLine();
                if (rgst.registerNick(nick) = true) {
                    break;
                } else {
                    System.out.println("닉네임을 다시 입력하여 주십시오");
                    return;
                }
            }

            while (true) {
                System.out.println("전화번호를 입력해주십시오 :");
                int num = sc.nextInt();
                if (rgst.registerNum(num) = true) {
                    break;
                } else {
                    System.out.println("전화번호를 다시 입력하여 주십시오");
                    return;
                }
            }

        }

    }
}
