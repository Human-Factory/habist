package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.service.MyInformationService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.myinformationview.MyInfomationInputView;

import java.util.Scanner;

public class MyInformationController {
    private MyInformationService myInformationService;
    public MyInformationController(MyInformationService myInformationService) {
        this.myInformationService = myInformationService;
    }
    Scanner sc = new Scanner(System.in);

    public boolean verifyPassword(String password) {
        return myInformationService.verifyPassword(password);
    }

    public void updateNickname(String newNickname) {
        boolean result = myInformationService.updateNickname(newNickname);
        if (result) {
            System.out.println("✅ 닉네임이 변경되었습니다.");
        } else {
            System.out.println("🚨 닉네임 변경에 실패했습니다.");
        }

    }

    public void updateEmail(String newEmail) {
        boolean result = myInformationService.updateEmail(newEmail);
        if (result) {
            System.out.println("✅ 이메일이 변경되었습니다.");
        } else {
            System.out.println("🚨 이메일 변경에 실패했습니다.");
        }
    }

    public void updatePhone(String newPhone) {
        boolean result = myInformationService.updatePhone(newPhone);
        if (result) {
            System.out.println("✅ 전화번호가 변경되었습니다.");
        } else {
            System.out.println("🚨 전화번호 변경에 실패했습니다.");
        }
    }

    public void updatePassword(String newPassword) {
        boolean result = myInformationService.updatePassword(newPassword);
        if (result) {
            System.out.println("✅ 비밀번호가 변경되었습니다.");
        } else {
            System.out.println("🚨 비밀번호 변경에 실패했습니다.");
        }
    }
}
