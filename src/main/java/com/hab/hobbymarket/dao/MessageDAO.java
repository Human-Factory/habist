package com.hab.hobbymarket.dao;


import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    // 1. 쪽지 보내기 (INSERT)
    public boolean save(Long senderId, Long receiverId, String content) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, content) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, senderId);
            pstmt.setLong(2, receiverId);
            pstmt.setString(3, content);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("쪽지 전송 실패: " + e.getMessage());
            return false;
        }
    }

    // 2. 받은 쪽지 조회 (SELECT)
    public List<Message> findReceivedMessages(Long receiverId) {
        List<Message> list = new ArrayList<>();
        String sql = """
                SELECT message_id, sender_id, receiver_id, content, is_read, created_at
                FROM messages
                WHERE receiver_id = ?
                ORDER BY created_at DESC
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, receiverId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Message(
                            rs.getLong("message_id"),
                            rs.getLong("sender_id"),
                            rs.getLong("receiver_id"),
                            rs.getString("content"),
                            rs.getBoolean("is_read"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("받은 쪽지 조회 실패: " + e.getMessage());
        }
        return list;
    }

    // 3. 보낸 쪽지 조회 (SELECT)
    public List<Message> findSentMessages(Long senderId) {
        List<Message> list = new ArrayList<>();
        String sql = """
                SELECT message_id, sender_id, receiver_id, content, is_read, created_at
                FROM messages
                WHERE sender_id = ?
                ORDER BY created_at DESC
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, senderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Message(
                            rs.getLong("message_id"),
                            rs.getLong("sender_id"),
                            rs.getLong("receiver_id"),
                            rs.getString("content"),
                            rs.getBoolean("is_read"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("보낸 쪽지 조회 실패: " + e.getMessage());
        }
        return list;
    }

    // 4. 읽음 처리 (UPDATE)
    public boolean markAsRead(Long messageId, Long receiverId) {
        String sql = "UPDATE messages SET is_read = 1 WHERE message_id = ? AND receiver_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, messageId);
            pstmt.setLong(2, receiverId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("읽음 처리 실패: " + e.getMessage());
            return false;
        }
    }
}
