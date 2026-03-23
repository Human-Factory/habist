package com.hab.hobbymarket.dao;

import com.hab.hobbymarket.model.InstructorApplication;

import java.time.LocalDateTime;
import java.util.List;

public interface InstructorDAO {

    // 강의자 신청 저장 (REQ-INS-003)
    void save(InstructorApplication application);

    // 신청 목록 전체 조회 - 관리자용 (REQ-INS-004)
    List<InstructorApplication> findAll();

    // 신청 상태 변경 - APPROVED 또는 REJECTED (REQ-INS-004)
    void updateStatus(Long applicationId, String status, LocalDateTime processedAt);

    // 회원의 role을 INSTRUCTOR로 변경 (승인 시) (REQ-INS-004)
    void updateMemberRole(Long memberId, String role);

    // 내 신청 상태 조회 - 강의자용 (REQ-INS-005)
    InstructorApplication findByMemberId(Long memberId);
}