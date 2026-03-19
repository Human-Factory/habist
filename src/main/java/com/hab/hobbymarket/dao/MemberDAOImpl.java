package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

        // 더미 데이터 저장소 (DB 대신 메모리에서 관리)
        private static List<Member> memberList = new ArrayList<>();

        // 아이디 중복 체크
        @Override
        public boolean existsByLoginId(String loginId) {
            for (Member member : memberList) {
                if (member.getLoginId().equals(loginId)) {
                    return true;  // 있으면?
                }
            }
            return false;  // 없으면?
        }

        // 회원 저장
        @Override
        public void save(Member member) {
            memberList.add(member);  // memberList에 추가하려면?
        }
}


