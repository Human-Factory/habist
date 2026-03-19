package com.hab.hobbymarket.dao;

import com.hab.global.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class  MemberDAOImpl{

    public int deactivateMember(int memberId) {


        // 성공하면 1, 실패 0
        int result = 0;
        // DB 연결 객체
        Connection conn = null;
        // sql 실행 객체
        PreparedStatement pstmt = null;


        // 회원 번호(member_id)가 일치하는 회원의 상태를
        // INACTIVE로 변경하는 SQL문
        String sql = "UPDATE members SET status = 'INACTIVE' WHERE member_id = ?";


        // 현재 아직 로그인이 완성이 안됐기 때문에 데이터 말소 기능은 안넣은 상태입니다. 로그인 기능이 구현 됐을 때 추가하기
        //String sql = "UPDATE members SET status = 'INACTIVE' WHERE member_id = ? AND status = 'ACTIVE'";

        try {
            //DB연결
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            // 첫 번째 자리에 memberId를 넣는다.
            pstmt.setInt(1, memberId);

            // 실행 후 result에 저장
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
