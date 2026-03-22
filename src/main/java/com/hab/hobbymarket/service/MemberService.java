package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.MemberDAO;
import com.hab.hobbymarket.dao.MemberDAOImpl;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.MemberSignUpRequest;

public class MemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();

    // 회원가입
    public void signUp(MemberSignUpRequest request) {

        // 1. 아이디 형식 검증
        String loginId = request.getLoginId();

        if (loginId.length() < 5 || loginId.length() > 20) {
            System.out.println("아이디는 5~20자여야 합니다.");
            return;
        }

        if (!loginId.matches("[a-zA-Z0-9]+")) {
            System.out.println("아이디는 영문+숫자만 가능합니다.");
            return;
        }

        // 2. 비밀번호 형식 검증
        String password = request.getPassword();

        if (password.length() <= 8) {
            System.out.println("비밀번호는 8자 이상이어야 합니다.");
            return;
        }

        if (!password.matches(".*[a-zA-Z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[!@#$%^&*].*")) {
            System.out.println("비밀번호 형식이 올바르지 않습니다.");
            return;
        }

        // 3. 아이디 중복 체크
        if (memberDAO.existsByLoginId(loginId)) {
            System.out.println("이미 사용 중인 아이디입니다.");
            return;
        }

        // 4. Member 객체 생성 후 저장
        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getNickname(),
                request.getName()
        );
        memberDAO.save(member);
        System.out.println("회원가입 성공!");
    }

    // 회원 탈퇴
    public boolean deleteMember(int memberId) {

        // 1. 회원 존재 여부 확인
        boolean exists = memberDAO.existsByMemberId(memberId);

        // 존재하지 않는 회원이면 바로 실패
        if (!exists) {
            return false;
        }

        // 2. 회원과 연결된 자식 데이터 먼저 삭제
        memberDAO.deleteWishlistsByMemberId(memberId);
        memberDAO.deleteEnrollmentsByMemberId(memberId);
        memberDAO.deleteSubscriptionsByMemberId(memberId);

        // 3. 마지막으로 회원 정보 삭제
        int result = memberDAO.deleteMembersByMemberId(memberId);

        // 4. members 삭제 성공 여부 반환
        return result > 0;
    }
}
