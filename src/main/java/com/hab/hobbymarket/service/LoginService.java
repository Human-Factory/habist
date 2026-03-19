package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.*;
import com.hab.hobbymarket.model.LoginAttempt;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

import java.time.LocalDateTime;

public class LoginService {

    private final MemberDAO memberDAO = new MemberDAOImpl();
    private final LoginAttemptDAO attemptDAO = new LoginAttemptDAOImpl();

    // 로그인 처리
    public boolean login(String id, String pwd) {

        // 1. 로그인 시도 정보 조회
        LoginAttempt attempt = attemptDAO.findByLoginId(id);

        // 2. 없으면 생성
        if (attempt == null) {
            attemptDAO.insert(id);
            attempt = attemptDAO.findByLoginId(id);
        }

        // 3. 잠금 상태 확인
        if (attempt.getLockedUntil() != null &&
                attempt.getLockedUntil().isAfter(LocalDateTime.now())) {

            System.out.println("계정이 잠겨 있습니다. 잠시 후 다시 시도하세요.");
            return false;
        }

        // 4. 회원 조회
        Member member = memberDAO.findByLoginId(id);

        // 5. 로그인 실패 (아이디 없음 or 비번 틀림)
        if (member == null || !member.getPassword().equals(pwd)) {

            int newCount = attempt.getAttemptCount() + 1;

            // 실패 횟수 증가
            attemptDAO.increaseAttemptCount(id, newCount);

            // 5회 초과 시 잠금 (10분)
            if (newCount >= 5) {
                LocalDateTime lockTime = LocalDateTime.now().plusMinutes(10);
                attemptDAO.updateLock(id, lockTime.toString());

                System.out.println("로그인 5회 실패로 계정이 10분간 잠깁니다.");
            } else {
                System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
            }

            return false;
        }

        // 6. 계정 상태 확인
        if (!member.getStatus().equals(Member.STATUS_ACTIVE)) {
            System.out.println("비활성화된 계정입니다.");
            return false;
        }

        // 7. 로그인 성공 → 초기화 + 세션 저장
        attemptDAO.reset(id);
        SessionManager.setCurrentUser(member);

        System.out.println("로그인 성공");
        return true;
    }
}