package com.thejoeun.practice1.boilerplate1.service;

import com.thejoeun.practice1.boilerplate1.config.jwt.JwtTokenProvider;
import com.thejoeun.practice1.boilerplate1.model.Member;
import com.thejoeun.practice1.boilerplate1.model.dto.JwtTokenDto;
import com.thejoeun.practice1.boilerplate1.model.dto.MemberRequestDto;
import com.thejoeun.practice1.boilerplate1.model.dto.MemberResponseDto;
import com.thejoeun.practice1.boilerplate1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto signup(MemberRequestDto request) {
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = request.toMember(passwordEncoder);

        return MemberResponseDto.of(memberRepository.save(member));
    }

    public JwtTokenDto login(MemberRequestDto request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        JwtTokenDto jwtTokenDto = jwtTokenProvider.generateTokenDto(authentication);

        Optional<Member> optMember = memberRepository.findByEmail(request.getEmail());
        Member member = optMember.orElse(null);

        if(Objects.nonNull(member)) {
            member.setAccessToken(jwtTokenDto.getAccessToken());
            member.setAccessTokenExpireIn(jwtTokenDto.getTokenExpiresIn());
            memberRepository.save(member);
        }

        return jwtTokenDto;
    }
}
