
/* comment.
    DB가 잘 구동되는지 확인하기 위해서 만들어진 Testing
    파일입니다. 정상 작동 하는 과정 확인했습니다.
 */

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