package com.hab.hobbymarket.service;

import java.sql.Connection;

public class MemberService {

    private Connection con;

    public MemberService(Connection con) {
        this.con = con;
    }
}
