package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Lecture;
import com.hab.hobbymarket.model.LectureCreateRequest;
import com.hab.hobbymarket.model.LectureUpdateRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LectureDAOImpl implements LectureDAO {

    @Override
    public int insertLecture(long instructorId, LectureCreateRequest request) {
        String sql = """
                INSERT INTO lectures (instructor_id, category_id, title, description)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, instructorId);
            pstmt.setInt(2, request.getCategoryId());
            pstmt.setString(3, request.getTitle());
            pstmt.setString(4, request.getDescription());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 등록 실패: " + e.getMessage());
            return 0;
        }
    }

    // 강의자 본인만 수정 가능
    @Override
    public int updateLectureByInstructor(long instructorId, LectureUpdateRequest request) {
        String sql = """
                UPDATE lectures
                SET category_id = ?, title = ?, description = ?, updated_at = NOW()
                WHERE lecture_id = ? AND instructor_id = ? AND is_deleted = 0
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, request.getCategoryId());
            pstmt.setString(2, request.getTitle());
            pstmt.setString(3, request.getDescription());
            pstmt.setInt(4, request.getLectureId());
            pstmt.setLong(5, instructorId);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 수정 실패(강의자): " + e.getMessage());
            return 0;
        }
    }

    // 관리자는 어떤 강의든 수정 가능
    @Override
    public int updateLectureByAdmin(LectureUpdateRequest request) {
        String sql = """
                UPDATE lectures
                SET category_id = ?, title = ?, description = ?, updated_at = NOW()
                WHERE lecture_id = ? AND is_deleted = 0
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, request.getCategoryId());
            pstmt.setString(2, request.getTitle());
            pstmt.setString(3, request.getDescription());
            pstmt.setInt(4, request.getLectureId());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 수정 실패(관리자): " + e.getMessage());
            return 0;
        }
    }

    // 강의자 본인만 삭제 가능
    @Override
    public int softDeleteLectureByInstructor(long instructorId, int lectureId) {
        String sql = """
                UPDATE lectures
                SET is_deleted = 1, updated_at = NOW()
                WHERE lecture_id = ? AND instructor_id = ? AND is_deleted = 0
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, lectureId);
            pstmt.setLong(2, instructorId);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 삭제 실패(강의자): " + e.getMessage());
            return 0;
        }
    }

    // 관리자는 어떤 강의든 삭제 가능
    @Override
    public int softDeleteLectureByAdmin(int lectureId) {
        String sql = """
                UPDATE lectures
                SET is_deleted = 1, updated_at = NOW()
                WHERE lecture_id = ? AND is_deleted = 0
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, lectureId);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 삭제 실패(관리자): " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Lecture> findAllLectures() {

        String sql = "SELECT l.lecture_id, l.title, m.name AS instructor_name, l.view_count, l.like_count "
                + "FROM lectures l "
                + "JOIN members m ON l.instructor_id = m.member_id "
                + "WHERE l.is_deleted = 0 "
                + "ORDER BY l.lecture_id DESC";

        List<Lecture> lectures = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Lecture lecture = new Lecture(
                        rs.getInt("lecture_id"),
                        rs.getString("title"),
                        rs.getString("instructor_name"),
                        rs.getInt("view_count"),
                        rs.getInt("like_count")
                );

                lectures.add(lecture);
            }

        } catch (Exception e) {
            System.out.println("강의 목록 조회 실패: " + e.getMessage());
        }

        return lectures;
    }
}
