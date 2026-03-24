package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Enrollment {


    private int enrollmentId;     // 수강 번호
    private int memberId;         // 회원 번호
    private int lectureId;        // 강의 번호
    private String lectureTitle;  // 강의 제목
    private int progressPercent;  // 학습 진척도
    private String lastPosition;  // 마지막 학습 위치
    private Timestamp enrolledAt; // 수강 시작일

    public Enrollment(int enrollmentId, int memberId, int lectureId, String title, int progressPercent, String lastPosition, java.sql.Timestamp enrolledAt) {}

    public Enrollment(int enrollmentId, int memberId, int lectureId, String lectureTitle, int progressPercent, String lastPosition, Timestamp enrolledAt) {
        this.enrollmentId = enrollmentId;
        this.memberId = memberId;
        this.lectureId = lectureId;
        this.lectureTitle = lectureTitle;
        this.progressPercent = progressPercent;
        this.lastPosition = lastPosition;
        this.enrolledAt = enrolledAt;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public String getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(String lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Timestamp getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Timestamp enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    @Override
    public String toString() {
        return "Enrollement{" +
                "enrollmentId=" + enrollmentId +
                ", memberId=" + memberId +
                ", lectureId=" + lectureId +
                ", lectureTitle='" + lectureTitle + '\'' +
                ", progressPercent=" + progressPercent +
                ", lastPosition='" + lastPosition + '\'' +
                ", enrolledAt=" + enrolledAt +
                '}';
    }
}
