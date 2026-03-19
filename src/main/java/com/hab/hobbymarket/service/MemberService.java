package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.MemberDAO;
import com.hab.hobbymarket.dao.MemberDAOImpl;

public class MemberService {
    // DAO 인터페이스 타입으로 선언
    // 실제 구현체는 MemberDAOImpl 사용
    private final MemberDAOImpl memberDAO = new MemberDAOImpl();

    public boolean deactivateMember(int memberId) {

        // DAO에게 memberId를 넘겨서 DB 업데이트 실행
        int result = memberDAO.deactivateMember(memberId);

        // 업데이트된 행이 1개 이상이면 true 반환
        // 즉, 회원 상태 변경 성공
        return result > 0;
    }
}
