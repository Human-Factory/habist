/** MySQL은 8.3.X의 방식을 사용했습니다.
 *  [진행 흐름 요약]
 *      1. 프로그램 시작
 *      2. static 블록 : db.properties 파일을 읽어서 수첩을 저장
 *      3. 누군가 getConnection() 호출 후 수첩에서 주소 / 계정 꺼내서 DB 연결
 *      4. SQL 작업 수행
 *      5. close() 로 연결 정리
 */

package com.hab.global.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    // db.properties 내용을 넣을 저장소를 제작합니다.
    private static final Properties properties = new Properties();

    // static 특성인 클래스가 처음 사용될 때 딱 1번만 자동으로 실행되게 만든다.
    static {
        try {
            // db.properties에서 읽기 통로를 생성
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            // 파일을 찾았다면, Properties에 적는다.
            if (input != null) {
                properties.load(input);

            // 만약 찾지 못한다면 에러 메세지를 출력
            } else {
                System.out.println("🚨db.properties 파일을 찾을 수 없습니다.🚨");
            }
          // 하지만 에러로 인해서 프로그램이 죽으면 안되니까 try - catch 구문을 통해 예외 처리
        } catch (Exception e) {
            System.out.println("🚨설정 파일 로드 실패🚨: " + e.getMessage());
        }
    }
    // DB 연결 만들기
    public static Connection getConnection() {
        // 시스템이 죽으면 안되기 때문에 예외처리 진행
        try {
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
          // 만약 에러가 발생 시 에러 매세지를 호출
        } catch (SQLException e) {
            System.out.println("🚨DB 연결 실패🚨: " + e.getMessage());
            return null;
        }
    }
    // 사용한 DB 닫기
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            // try - catch 구문으로 예외 처리
            try {
                if (resource != null) resource.close();
            } catch (Exception e) {
                // 무시
            }
        }
    }
}