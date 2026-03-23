package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.LectureCreateRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LectureDAOImpl implements LectureDAO {

//   lectures 테이블에 새로운 강의를 INSERT 한다.
//    등록자(instructor_id)는 사용자가 직접 입력하는 값이 아니라
//    현재 로그인한 사용자 정보를 바탕으로 Service에서 전달받는다.
    @Override
    public int insertLecture(long instructorId, LectureCreateRequest request) {

        // 강의 등록 SQL
        // 기본값 컬럼(view_count, like_count, is_deleted 등)은 DB 설정에 맡김
        String sql = """
                INSERT INTO lectures (instructor_id, category_id, title, description)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 강의자 회원 번호
            pstmt.setLong(1, instructorId);

            // 카테고리 번호
            pstmt.setInt(2, request.getCategoryId());

            // 강의 제목
            pstmt.setString(3, request.getTitle());

            // 강의 설명
            pstmt.setString(4, request.getDescription());

            // INSERT 실행 후 영향받은 행 수 반환
            return pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("강의 등록 실패: " + e.getMessage());
            return 0;
        }
    }
}