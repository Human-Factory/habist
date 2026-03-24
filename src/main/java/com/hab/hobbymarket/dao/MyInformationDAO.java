package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MyInformationDAO {

    public boolean updateNickname(Long memberId, String newNickname) {

        String sql = "UPDATE members SET nickname = ? WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newNickname);
            pstmt.setLong(2, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("닉네임 수정 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean updateEmail(Long memberId, String newEmail) {
        String sql = "UPDATE members SET email = ? WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setLong(2, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("이메일 수정 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePhone(Long memberId, String newPhone) {
        String sql = "UPDATE members SET phone = ? WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPhone);
            pstmt.setLong(2, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("전화번호 수정 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePassword(Long memberId, String newPassword) {
        String sql = "UPDATE members SET password = ? WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setLong(2, memberId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("비밀번호 수정 실패: " + e.getMessage());
            return false;
        }
    }
}
