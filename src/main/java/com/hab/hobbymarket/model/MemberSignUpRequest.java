package com.hab.hobbymarket.model;

public class MemberSignUpRequest {

    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private String name;
    private String phone;

    // 생성자
    public MemberSignUpRequest(String loginId, String password, String email,
                               String nickname, String name, String phone) {
        this.loginId  = loginId;
        this.password = password;
        this.email    = email;
        this.nickname = nickname;
        this.name     = name;
        this.phone    = phone;
    }

    // Getter
    public String getLoginId()  {
        return loginId;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail()    {
        return email;
    }
    public String getNickname() {
        return nickname;
    }
    public String getName()     {
        return name;
    }
    public String getPhone()    {
        return phone;
    }
}
