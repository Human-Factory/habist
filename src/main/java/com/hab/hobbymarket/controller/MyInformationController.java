package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.MyInformationService;
import com.hab.hobbymarket.view.myinformationview.MyInfomationInputView;

public class MyInformationController {
    private MyInformationService myInformationService;
    public MyInformationController(MyInformationService myInformationService) {
        this.myInformationService = myInformationService;
    }

    public boolean verifyPassword(String password) {
        return myInformationService.verifyPassword(password);
    }

    public void updateNickname() {
    }

    public void updateEmail() {
    }

    public void updatePhone() {
    }

    public void updatePwd() {
    }
}
