package com.blog.react_spring_blog.entity;


//회원 관련 로직
import com.blog.react_spring_blog.common.BaseTimeEntity;
import com.blog.react_spring_blog.common.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    //이메일로 로그인 함
    @Column(nullable = false)
    private String email;

    //패스워드
    @Column(nullable = false)
    private String password;

    //사용자 이름
    @Column(nullable = false)
    private String username;

    //권한
    @Enumerated(EnumType.STRING)
    private Role roles;

    //JPA 연관관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<>


    //(1)게시판
    //(2)댓글

    //생성자 Builder
    @Builder
    public Member(Long id, String email, String password, String username, Role roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }
    //---------------------------------------------------
    //회원정보수정 - 메서드
    public void update(String password, String username){
        this.password = password;
        this.username = username;
    }

    /*
     * Token을 고유한 Email 값으로 생성하기
     * @return email
     * */
    @Override
    public String getusername() {
        return email;
    }

    // ------ UserDetails implements ------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities
                = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.roles.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}





