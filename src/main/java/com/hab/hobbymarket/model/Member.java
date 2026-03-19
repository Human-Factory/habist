package com.hab.hobbymarket.model;

import java.time.LocalDateTime;

public class Member {

    // -----------------------------------------------
    // 역할 상수 (role 필드에 넣을 수 있는 값)
    // -----------------------------------------------
    public static final String ROLE_USER       = "USER";
    public static final String ROLE_PREMIUM    = "PREMIUM";
    public static final String ROLE_INSTRUCTOR = "INSTRUCTOR";
    public static final String ROLE_ADMIN      = "ADMIN";

    // -----------------------------------------------
    // 상태 상수 (status 필드에 넣을 수 있는 값)
    // -----------------------------------------------
    public static final String STATUS_ACTIVE   = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_BANNED   = "BANNED";

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
    private String role;        // 역할: USER, PREMIUM, INSTRUCTOR, ADMIN
    private String status;      // 상태: ACTIVE, INACTIVE, BANNED
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
        this.role      = ROLE_USER;      // 가입하면 기본값 USER
        this.status    = STATUS_ACTIVE;  // 가입하면 기본값 ACTIVE
        this.createdAt = LocalDateTime.now();
    }

    // -----------------------------------------------
    // Getter
    // -----------------------------------------------
    public Long getMemberId()           { return memberId; }
    public String getLoginId()          { return loginId; }
    public String getPassword()         { return password; }
    public String getNickname()         { return nickname; }
    public String getName()             { return name; }
    public String getEmail()            { return email; }
    public String getPhone()            { return phone; }
    public String getRole()             { return role; }
    public String getStatus()           { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // -----------------------------------------------
    // Setter
    // -----------------------------------------------
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public void setEmail(String email)     { this.email = email; }
    public void setPhone(String phone)     { this.phone = phone; }

    // ------------------------------------------------
    // DB 조회용 생성자
    // ------------------------------------------------
    public Member(Long memberId, String loginId, String password, String nickname,
                  String name, String email, String phone, String role, String status,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
