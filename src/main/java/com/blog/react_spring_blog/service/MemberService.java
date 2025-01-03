package com.blog.react_spring_blog.service;

//!!

import com.blog.react_spring_blog.common.exception.MemberException;
import com.blog.react_spring_blog.repository.MemberRepository;
import com.blog.react_spring_blog.security.jwt.CustomUserDetailsService;
import com.blog.react_spring_blog.security.jwt.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    //리파지토리
    private final MemberRepository memberRepository;

    //인증
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    //패스워드 인코딩
    private final PasswordEncoder encoder;

    //토큰
    private  final JwtTokenUtil jwtTokenUtil;


    //메서드-1
    //메서드-2
    //메서드-3
    //메서드-4

    //--------------■사용자 인증(파라미터 email/password)
    //authenticationManager -> authenticate()
    //UsernamePasswordAuthenticationToken 구현객체 활용
    private void authenticate(String email, String pwd){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,pwd));
        }catch (DisabledException e){ //로그인 실패 시, 대응 예외
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        }catch (BadCredentialsException e){ //사용자 인증 시, 적절하지 않을 때 예외
            throw new MemberException("비밀번호가 일치하지 않습니다..", HttpStatus.BAD_REQUEST);
        }
    }

    //아이디(이메일)중복 체크
    // @param email
    private  void  isExistUserEmail(String email){
        if(memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    //비밀번호,비밀번호 확인 같은지 체크
    //@param password
     //@param passwordCheck
    private void  checkPassword(String password, String passwordCheck){
        if(!password.equals(passwordCheck)){
            throw new MemberException("패스워드 불일치",HttpStatus.BAD_REQUEST);
        }
    }



    //사용자 입력한 비밀번호, DB에 저장된 비밀번호 같은지 체크: 인코딩 확인

}
