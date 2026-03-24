package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.InstructorApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {

    // 강의자 신청 저장
    @Override
    public void save(InstructorApplication application) {
        String sql = "INSERT INTO instructor_applications (member_id, status, created_at) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, application.getMemberId());
            pstmt.setString(2, application.getStatus());
            pstmt.setTimestamp(3, Timestamp.valueOf(application.getCreatedAt()));
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의자 신청 저장 실패: " + e.getMessage());
        }
    }

    // 신청 목록 전체 조회 (관리자용)
    @Override
    public List<InstructorApplication> findAll() {
        String sql = "SELECT application_id, member_id, certification_file, status, created_at, processed_at FROM instructor_applications";
        List<InstructorApplication> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new InstructorApplication(
                        rs.getLong("application_id"),
                        rs.getLong("member_id"),
                        rs.getString("certification_file"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("processed_at") == null ? null :
                                rs.getTimestamp("processed_at").toLocalDateTime()
                ));
            }

        } catch (Exception e) {
            System.out.println("신청 목록 조회 실패: " + e.getMessage());
        }

        return list;
    }

    // 신청 상태 변경 (APPROVED 또는 REJECTED)
    @Override
    public void updateStatus(Long applicationId, String status, LocalDateTime processedAt) {
        String sql = "UPDATE instructor_applications SET status = ?, processed_at = ? WHERE application_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setTimestamp(2, Timestamp.valueOf(processedAt));
            pstmt.setLong(3, applicationId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("신청 상태 변경 실패: " + e.getMessage());
        }
    }

    // 회원 role 변경 (INSTRUCTOR로)
    @Override
    public void updateMemberRole(Long memberId, String role) {
        String sql = "UPDATE members SET role = ? WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, role);
            pstmt.setLong(2, memberId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("회원 role 변경 실패: " + e.getMessage());
        }
    }

    // 내 신청 상태 조회 (강의자용)
    @Override
    public InstructorApplication findByMemberId(Long memberId) {
        String sql = "SELECT application_id, member_id, certification_file, status, created_at, processed_at FROM instructor_applications WHERE member_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new InstructorApplication(
                            rs.getLong("application_id"),
                            rs.getLong("member_id"),
                            rs.getString("certification_file"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("processed_at") == null ? null :
                                    rs.getTimestamp("processed_at").toLocalDateTime()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("신청 조회 실패: " + e.getMessage());
        }

        return null;
    }
}
