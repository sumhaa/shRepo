package com.blog.react_spring_blog.security.jwt;


import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* DaoAuthenticationProvider 구현
* */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //멤버
    @Autowired
    private MemberRepository memberRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.memberRepo.findByEmail(username).orElseThrow(
                ()-> new ResourceNotFoundException("Member", "Member Email: ", username));
    }
}
