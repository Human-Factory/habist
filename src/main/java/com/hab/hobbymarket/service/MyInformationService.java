package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.MyInformationDAO;
import com.hab.hobbymarket.session.SessionManager;

import java.sql.Connection;

public class MyInformationService {
    private final Connection con;
    private final MyInformationDAO myInformationDAO = new MyInformationDAO();

    public MyInformationService(Connection con) {
        this.con = con;
    }

    public boolean verifyPassword(String password) {
        String currentPassword = SessionManager.getCurrentUser().getPassword();
        return currentPassword.equals(password);
    }

    public boolean updateNickname(String newNickname) {

        // 1. 현재 로그인한 유저 memberId 가져오기
        Long memberId = SessionManager.getCurrentUser().getMemberId();

        // 2. DB 업데이트 결과를 result에 저장
        boolean result = myInformationDAO.updateNickname(memberId, newNickname);

        // 3. DB 업데이트 성공했을 때만 SessionManager도 업데이트
        if (result) {
            // SessionManager에서 현재 유저 꺼내서
            // 닉네임을 새 닉네임으로 변경
            SessionManager.getCurrentUser().setNickname(newNickname);
        }

        // 4. 결과 반환
        return result;

    }

    public boolean updateEmail(String newEmail) {

        // 1. 현재 로그인한 유저 memberId 가져오기
        Long memberId = SessionManager.getCurrentUser().getMemberId();

        // 2. DB 업데이트 결과를 result에 저장
        boolean result = myInformationDAO.updateEmail(memberId, newEmail);

        // 3. DB 업데이트 성공했을 때만 SessionManager도 업데이트
        if (result) {
            // SessionManager에서 현재 유저 꺼내서
            // 닉네임을 새 닉네임으로 변경
            SessionManager.getCurrentUser().setEmail(newEmail);
        }

        // 4. 결과 반환
        return result;

    }

    public boolean updatePhone(String newPhone) {

        // 1. 현재 로그인한 유저 memberId 가져오기
        Long memberId = SessionManager.getCurrentUser().getMemberId();

        // 2. DB 업데이트 결과를 result에 저장
        boolean result = myInformationDAO.updatePhone(memberId, newPhone);

        // 3. DB 업데이트 성공했을 때만 SessionManager도 업데이트
        if (result) {
            // SessionManager에서 현재 유저 꺼내서
            // 닉네임을 새 닉네임으로 변경
            SessionManager.getCurrentUser().setPhone(newPhone);
        }

        // 4. 결과 반환
        return result;

    }

    public boolean updatePassword(String newPassword) {

        // 1. 현재 로그인한 유저 memberId 가져오기
        Long memberId = SessionManager.getCurrentUser().getMemberId();

        // 2. DB 업데이트 결과를 result에 저장
        boolean result = myInformationDAO.updatePassword(memberId, newPassword);

        // 3. DB 업데이트 성공했을 때만 SessionManager도 업데이트
        if (result) {
            // SessionManager에서 현재 유저 꺼내서
            // 닉네임을 새 닉네임으로 변경
            SessionManager.getCurrentUser().setPassword(newPassword);
        }

        // 4. 결과 반환
        return result;

    }



}
