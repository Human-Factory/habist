package com.hab.hobbymarket.domain;

import java.time.LocalDateTime;

public class Member {

    // -----------------------------------------------
    // 필드
    // -----------------------------------------------
    private Long memberId;
    private String loginId;     // 아이디: 영문+숫자 6~20자
    private String password;    // 비밀번호 (암호화해서 저장)
    private String nickname;    // 닉네임
    private String name;        // 이름: 한글 5글자 이내
    private String email;       // 이메일 (선택)
    private String phone;       // 전화번호 (선택)
    private Role role;          // 역할: USER, PREMIUM, INSTRUCTOR, ADMIN
    private Status status;      // 상태: ACTIVE, INACTIVE, BANNED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // -----------------------------------------------
    // 생성자
    // -----------------------------------------------
    public Member(String loginId, String password, String nickname, String name) {
        this.loginId   = loginId;
        this.password  = password;
        this.nickname  = nickname;
        this.name      = name;
        this.role      = Role.USER;        // 가입하면 기본값 USER
        this.status    = Status.ACTIVE;    // 가입하면 기본값 ACTIVE
        this.createdAt = LocalDateTime.now();
    }

    // -----------------------------------------------
    // Getter (값을 꺼내볼 때 사용)
    // -----------------------------------------------
    public Long getMemberId()         { return memberId; }
    public String getLoginId()        { return loginId; }
    public String getPassword()       { return password; }
    public String getNickname()       { return nickname; }
    public String getName()           { return name; }
    public String getEmail()          { return email; }
    public String getPhone()          { return phone; }
    public Role getRole()             { return role; }
    public Status getStatus()         { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // -----------------------------------------------
    // Setter (값을 바꿀 때 사용)
    // -----------------------------------------------
    public void setMemberId(Long memberId)   { this.memberId = memberId; }
    public void setEmail(String email)       { this.email = email; }
    public void setPhone(String phone)       { this.phone = phone; }

    // -----------------------------------------------
    // 비즈니스 메서드 (기능)
    // -----------------------------------------------

    // 비밀번호 변경
    public void updatePassword(String newPassword) {
        this.password  = newPassword;
        this.updatedAt = LocalDateTime.now();
    }

    // 개인정보 수정
    public void updateInfo(String nickname, String name, String phone, String email) {
        this.nickname  = nickname;
        this.name      = name;
        this.phone     = phone;
        this.email     = email;
        this.updatedAt = LocalDateTime.now();
    }

    // 프리미엄 업그레이드
    public void upgradeToPremium() {
        this.role      = Role.PREMIUM;
        this.updatedAt = LocalDateTime.now();
    }

    // 프리미엄 해지
    public void downgradeToUser() {
        this.role      = Role.USER;
        this.updatedAt = LocalDateTime.now();
    }

    // 회원 탈퇴 (비활성화)
    public void deactivate() {
        this.status    = Status.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    // 계정 정지
    public void ban() {
        this.status    = Status.BANNED;
        this.updatedAt = LocalDateTime.now();
    }

    // 계정 활성화
    public void activate() {
        this.status    = Status.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    // 프리미엄이거나 관리자면 true
    public boolean isPremium() {
        return this.role == Role.PREMIUM || this.role == Role.ADMIN;
    }

    // 관리자면 true
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    // -----------------------------------------------
    // ENUM (정해진 값만 쓸 때 사용하는 타입)
    // -----------------------------------------------

    // 회원 권한
    public enum Role {
        USER, PREMIUM, INSTRUCTOR, ADMIN
    }

    // 회원 상태
    public enum Status {
        ACTIVE, INACTIVE, BANNED
    }
}