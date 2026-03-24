package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.Member;

public interface MemberDAO {

    // 아이디 중복 체크 (있으면 true, 없으면 false)
    boolean existsByLoginId(String loginId);

    // 회원 저장 (성공 true, 실패 false)
    boolean save(Member member);

    // 로그인용 회원 조회
    Member findByLoginId(String loginId);

    // 회원 상태 변경
    int deactivateMember(int memberId);

    boolean existsByMemberId(int memberId);

    // 자식삭제 -> 부모삭제로 이루어져야하며 각 목록도 삭제
    int deleteWishlistsByMemberId(int memberId);
    int deleteEnrollmentsByMemberId(int memberId);
    int deleteSubscriptionsByMemberId(int memberId);
    int deleteMembersByMemberId(int memberId);

    // 비밀번호 변경
    // loginId + name 이 일치하는 회원의 비밀번호를 새 비밀번호로 변경
    boolean updatePassword(String loginId, String name, String newPassword);

    // 닉네임으로 회원 조회 (쪽지 수신자 검색용)
    Member findByNickname(String nickname);
}
