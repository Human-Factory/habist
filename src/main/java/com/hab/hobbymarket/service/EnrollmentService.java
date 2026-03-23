package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.EnrollmentDAO;
import com.hab.hobbymarket.model.Enrollment;

import java.sql.Connection;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentService(Connection con) {
        this.enrollmentDAO = new EnrollmentDAO(con);
    }

    // 내 학습 목록 조회
    public List<Enrollment> getMyEnrollments(int memberId) {
        return enrollmentDAO.findByMemberId(memberId);
    }

    // 이어보기 조회
    public Enrollment getMyEnrollment(int enrollmentId, int memberId) {
        return enrollmentDAO.findById(enrollmentId, memberId);
    }

    // 진척도 업데이트
    public boolean updateProgress(int enrollmentId, int memberId,
                                  int progressPercent, String lastPosition) {
        return enrollmentDAO.updateProgress(enrollmentId, memberId, progressPercent, lastPosition);
    }
}
