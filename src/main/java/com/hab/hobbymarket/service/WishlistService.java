package com.hab.hobbymarket.service;

import java.sql.Connection;

public class WishlistService {

    private Connection con;

    public WishlistService(Connection con) {
        this.con = con;
    }
}
