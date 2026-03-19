package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.LoginAttempt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class LoginAttemptDAOImpl implements LoginAttemptDAO {

    // 로그인 시도 조회
    @Override
    public LoginAttempt findByLoginId(String loginId) {

        String sql = """
                SELECT attempt_id, login_id, attempt_count, locked_until, last_attempt_at
                FROM login_attempts
                WHERE login_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new LoginAttempt(
                            rs.getLong("attempt_id"),
                            rs.getString("login_id"),
                            rs.getInt("attempt_count"),
                            rs.getTimestamp("locked_until") == null ? null :
                                    rs.getTimestamp("locked_until").toLocalDateTime(),
                            rs.getTimestamp("last_attempt_at") == null ? null :
                                    rs.getTimestamp("last_attempt_at").toLocalDateTime()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("로그인 시도 조회 실패: " + e.getMessage());
        }

        return null;
    }

    // 최초 로그인 시도 생성
    @Override
    public void insert(String loginId) {

        String sql = """
                INSERT INTO login_attempts (login_id, attempt_count, last_attempt_at)
                VALUES (?, 0, NOW())
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("로그인 시도 생성 실패: " + e.getMessage());
        }
    }

    // 실패 횟수 증가
    @Override
    public void increaseAttemptCount(String loginId, int attemptCount) {

        String sql = """
                UPDATE login_attempts
                SET attempt_count = ?, last_attempt_at = NOW()
                WHERE login_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attemptCount);
            pstmt.setString(2, loginId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("로그인 실패 횟수 증가 실패: " + e.getMessage());
        }
    }

    // 계정 잠금 설정
    @Override
    public void updateLock(String loginId, String lockedUntil) {

        String sql = """
                UPDATE login_attempts
                SET locked_until = ?
                WHERE login_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lockedUntil);
            pstmt.setString(2, loginId);

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("계정 잠금 설정 실패: " + e.getMessage());
        }
    }

    // 로그인 성공 시 초기화
    @Override
    public void reset(String loginId) {

        String sql = """
                UPDATE login_attempts
                SET attempt_count = 0, locked_until = NULL
                WHERE login_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("로그인 초기화 실패: " + e.getMessage());
        }
    }
}