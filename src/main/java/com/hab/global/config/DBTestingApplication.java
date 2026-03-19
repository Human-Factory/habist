package com.hab.global.config;


import java.sql.Connection;

public class DBTestingApplication {
    public static void main(String[] args) {

        // 주소, 계정, 비밀번호 입력받아서 MySQL에 테스트
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ DB 연결 성공! ✅");
        } else {
            System.out.println("🚨 DB 연결 실패! 🚨");
        }
        DBConnection.close(conn);
    }
}