package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.LoginAttemptDAO;
import com.hab.hobbymarket.dao.LoginAttemptDAOImpl;
import com.hab.hobbymarket.dao.MemberDAO;
import com.hab.hobbymarket.dao.MemberDAOImpl;
import com.hab.hobbymarket.model.LoginAttempt;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.session.SessionManager;

import java.time.LocalDateTime;

public class LoginService {

    private final MemberDAO memberDAO = new MemberDAOImpl();
    private final LoginAttemptDAO attemptDAO = new LoginAttemptDAOImpl();

    // 로그인 처리
    public LoginResult login(String id, String pwd) {

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
            return LoginResult.LOCKED;
        }

        // 4. 회원 조회
        Member member = memberDAO.findByLoginId(id);

        // 5. 아이디 없음 또는 비밀번호 틀림
        if (member == null || !member.getPassword().equals(pwd)) {

            int newCount = attempt.getAttemptCount() + 1;

            // 실패 횟수 증가
            attemptDAO.increaseAttemptCount(id, newCount);

            // 5회 이상이면 10분 잠금
            if (newCount >= 5) {
                LocalDateTime lockTime = LocalDateTime.now().plusMinutes(10);
                attemptDAO.updateLock(id, lockTime.toString());
                return LoginResult.LOCKED;
            }

            return LoginResult.INVALID_CREDENTIAL;
        }

        // 6. 계정 상태 확인
        if (!Member.STATUS_ACTIVE.equals(member.getStatus())) {
            return LoginResult.INACTIVE;
        }

        // 7. 로그인 성공 → 시도 초기화 + 세션 저장
        attemptDAO.reset(id);
        SessionManager.setCurrentUser(member);

        // 8. 관리자 계정인지 확인
        if (Member.ROLE_ADMIN.equals(member.getRole())) {
            System.out.println("관리자 계정으로 로그인했습니다.");
        }

        return LoginResult.SUCCESS;
    }
}