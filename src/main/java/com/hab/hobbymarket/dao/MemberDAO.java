package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.Member;

public interface MemberDAO {

    // 아이디 중복 체크 (있으면 true, 없으면 false)
    boolean existsByLoginId(String loginId);

    // 회원 저장
    void save(Member member);

    // 로그인용 회원 조회
    Member findByLoginId(String loginId);
}
