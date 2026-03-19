package com.hab.hobbymarket.service;

import java.sql.Connection;

public class SubscriptionService {

    private Connection con;

    public SubscriptionService(Connection con) {
        this.con = con;
    }
}
