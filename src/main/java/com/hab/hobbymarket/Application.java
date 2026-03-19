package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.ContentController;
import com.hab.hobbymarket.controller.MemberController;

public class Application {
    public static void main(String[] args) {

        ContentController controller = new ContentController();
        controller.showCategory();

        MemberController memberController = new MemberController();
        memberController.signUp();

    }
}