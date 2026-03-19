package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.MemberDAO;
import com.hab.hobbymarket.dao.MemberDAOImpl;
import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.MemberSignUpRequest;

public class MemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();
    public void signUp(MemberSignUpRequest request) {

        // 1. 아이디 형식 검증
        String loginId = request.getLoginId();

        // 길이 체크
        if (loginId.length() < 5 || loginId.length() > 20) {
            System.out.println("아이디는 5~20자여야 합니다.");
            return;  // 조건 안 맞으면 여기서 중단!
        }

        // 형식 체크 (영문+숫자만 허용)
        if (!loginId.matches("[a-zA-Z0-9]+")) {
            System.out.println("아이디는 영문+숫자만 가능합니다.");
            return;
        }

        // 2. 비밀번호 형식 검증
        String password = request.getPassword();
        if (password.length() < 8) {
            System.out.println("비밀번호는 8자 이상이여야 합니다.");
            return;
        }
        if (!password.matches(".*[a-zA-Z].*") ||   // 영문 포함?
                !password.matches(".*[0-9].*") ||        // 숫자 포함?
                !password.matches(".*[!@#$%^&*].*")) {   // 특수문자 포함?
            System.out.println("비밀번호 형식이 올바르지 않습니다.");
            return;
        }


        // 3. 아이디 중복 체크
        if (memberDAO.existsByLoginId(loginId)) {  // 뭘 넣어야 할까요?
            System.out.println("이미 사용 중인 아이디입니다.");
            return;
        }

// Member 객체 생성 후 저장
        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getNickname(),
                request.getName()
        );
        memberDAO.save(member);  // 뭘 넣어야 할까요?
        System.out.println("회원가입 성공!");

    }

}
