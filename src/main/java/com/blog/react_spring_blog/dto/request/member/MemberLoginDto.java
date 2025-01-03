package com.blog.react_spring_blog.dto.request.member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * -Register -
 * 로그인 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {

    private  String email;
    private  String password;

    @Builder
    public MemberLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
