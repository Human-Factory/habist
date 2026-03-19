package com.hab.hobbymarket.view.wishlistview;

import com.hab.hobbymarket.controller.WishlistController;
import java.util.Scanner;

public class WishlistInputView {

    private WishlistController wishlistController;
    private Scanner sc = new Scanner(System.in);

    public WishlistInputView(WishlistController wishlistController) {
        this.wishlistController = wishlistController;
    }
}
