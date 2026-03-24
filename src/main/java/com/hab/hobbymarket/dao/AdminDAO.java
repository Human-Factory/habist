package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.Member;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.hab.global.config.DBConnection;
import com.hab.hobbymarket.model.Notice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
    public class AdminDAO {

        private final Connection con;

        public AdminDAO(Connection con) {
            this.con = con;
        }

        // 회원 전체 조회
        public List<Member> findAllMembers() {
            List<Member> list = new ArrayList<>();

            String sql = "SELECT * FROM members ORDER BY member_id";

            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    Member member = new Member(
                            rs.getLong("member_id"),
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nickname"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
                    );
                    list.add(member);
                }
            } catch (Exception e) {
                System.out.println("회원 조회 실패: " + e.getMessage());
            } finally {
                DBConnection.close(rs, pstmt);
            }

            return list;
        }

        // 회원 상태 변경
        public boolean updateMemberStatus(long memberId, String status) {
            String sql = "UPDATE members SET status = ? WHERE member_id = ?";

            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, status);
                pstmt.setLong(2, memberId);

                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("회원 상태 변경 실패: " + e.getMessage());
                return false;
            } finally {
                DBConnection.close(pstmt);
            }
        }

        // 공지 전체 조회
        public List<Notice> findAllNotices() {

            // 조회 결과를 담을 리스트
            List<Notice> list = new ArrayList<>();

            // 공지사항 전체 조회 SQL
            String sql = """
            SELECT notice_id, title, content, created_at
            FROM notices
            ORDER BY notice_id DESC
            """;

            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // SQL 준비
                pstmt = con.prepareStatement(sql);

                // 실행
                rs = pstmt.executeQuery();

                // 조회 결과를 Notice 객체로 변환
                while (rs.next()) {
                    Notice notice = new Notice(
                            rs.getInt("notice_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("created_at")
                    );
                    // 리스트에 추가
                    list.add(notice);
                }

            } catch (Exception e) {
                System.out.println("공지 조회 실패: " + e.getMessage());
            } finally {
                DBConnection.close(rs, pstmt);
            }

            return list;
        }

        // 공지 등록
        public boolean insertNotice(String title, String content) {

            // 공지사항 INSERT 쿼리
            String sql = "INSERT INTO notices (title, content) VALUES (?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                // 첫 번째 ? → 제목
                pstmt.setString(1, title);

                // 두 번째 ? → 내용
                pstmt.setString(2, content);

                // 실행 → 성공하면 true 반환
                return pstmt.executeUpdate() > 0;

            } catch (Exception e) {
                System.out.println("공지 등록 실패: " + e.getMessage());
                return false;
            }
        }

        // 공지 수정
        public boolean updateNotice(int noticeId, String title, String content) {
            String sql = "UPDATE notices SET title = ?, content = ? WHERE notice_id = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setInt(3, noticeId);
                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("공지 수정 실패: " + e.getMessage());
                return false;
            }
        }

        // 공지 삭제
        public boolean deleteNotice(int noticeId) {
            String sql = "DELETE FROM notices WHERE notice_id = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, noticeId);
                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("공지 삭제 실패: " + e.getMessage());
                return false;
            }
        }

        // Q&A 등록
        public boolean insertQna(String title, String content) {
            String sql = "INSERT INTO qna (title, content) VALUES (?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("Q&A 등록 실패: " + e.getMessage());
                return false;
            }
        }

        // Q&A 수정
        public boolean updateQna(int qnaId, String title, String content) {
            String sql = "UPDATE qna SET title = ?, content = ? WHERE qna_id = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setInt(3, qnaId);
                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("Q&A 수정 실패: " + e.getMessage());
                return false;
            }
        }

        // Q&A 삭제
        public boolean deleteQna(int qnaId) {
            String sql = "DELETE FROM qna WHERE qna_id = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, qnaId);
                return pstmt.executeUpdate() > 0;
            } catch (Exception e) {
                System.out.println("Q&A 삭제 실패: " + e.getMessage());
                return false;
            }
        }
    }

