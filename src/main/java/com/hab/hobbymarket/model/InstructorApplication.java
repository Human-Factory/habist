package com.hab.hobbymarket.model;

import java.time.LocalDateTime;

public class InstructorApplication {

    // 상태 상수
    public static final String STATUS_PENDING  = "PENDING";
    public static final String STATUS_APPROVED = "APPROVED";
    public static final String STATUS_REJECTED = "REJECTED";

    // 필드
    private Long applicationId;
    private Long memberId;
    private String certificationFile;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    // 신청용 생성자 - 회원이 강의자 신청할 때
    public InstructorApplication(Long memberId) {
        this.memberId  = memberId;
        this.status    = STATUS_PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // DB 조회용 생성자 - 전체 필드
    public InstructorApplication(Long applicationId, Long memberId, String certificationFile,
                                  String status, LocalDateTime createdAt, LocalDateTime processedAt) {
        this.applicationId     = applicationId;
        this.memberId          = memberId;
        this.certificationFile = certificationFile;
        this.status            = status;
        this.createdAt         = createdAt;
        this.processedAt       = processedAt;
    }

    // Getter / Setter
    public Long getApplicationId(){
        return applicationId; }
    public void setApplicationId(Long applicationId){
        this.applicationId = applicationId; }

    public Long getMemberId(){
        return memberId; }
    public void setMemberId(Long memberId){
        this.memberId = memberId; }

    public String getCertificationFile(){
        return certificationFile; }
    public void setCertificationFile(String certificationFile){
        this.certificationFile = certificationFile; }

    public String getStatus(){
        return status; }
    public void setStatus(String status){
        this.status = status; }

    public LocalDateTime getCreatedAt(){
        return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt; }

    public LocalDateTime getProcessedAt(){
        return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt){this.processedAt = processedAt; }
}
