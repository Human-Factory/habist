package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.service.WishlistService;

public class WishlistController {

    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }
}
