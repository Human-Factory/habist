package com.hab.hobbymarket.service;

import com.hab.hobbymarket.session.SessionManager;

import java.sql.Connection;

public class MyInformationService {
    private final Connection con;

    public MyInformationService(Connection con) {
        this.con = con;
    }

    public boolean verifyPassword(String password) {
        String currentPassword = SessionManager.getCurrentUser().getPassword();
        return currentPassword.equals(password);
    }
}
