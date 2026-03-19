package com.hab.hobbymarket.service;

import java.sql.Connection;

public class EnrollmentService {

    private Connection con;

    public EnrollmentService(Connection con) {
        this.con = con;
    }
}
