package com.hab.hobbymarket;

import com.hab.hobbymarket.controller.ContentController;

public class Application {
    public static void main(String[] args) {

        ContentController controller = new ContentController();
        controller.showCategory();

    }
}
