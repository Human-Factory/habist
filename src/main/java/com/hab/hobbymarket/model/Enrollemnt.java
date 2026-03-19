package com.hab.hobbymarket.model;

import java.security.Timestamp;

public class Enrollemnt {
    private int enrollmentId;
    private int memberId;
    private int lectureId;
    private int progressPercent;
    private String lastPosition;
    private Timestamp enrolledAt;

    public Enrollemnt(int enrollmentId, int memberId, int lectureId, int progressPercent, String lastPosition, Timestamp enrolledAt) {
        this.enrollmentId = enrollmentId;
        this.memberId = memberId;
        this.lectureId = lectureId;
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
        return "Enrollemnt{" +
                "enrollmentId=" + enrollmentId +
                ", memberId=" + memberId +
                ", lectureId=" + lectureId +
                ", progressPercent=" + progressPercent +
                ", lastPosition='" + lastPosition + '\'' +
                ", enrolledAt=" + enrolledAt +
                '}';
    }
}
