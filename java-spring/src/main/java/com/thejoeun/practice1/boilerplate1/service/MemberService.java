package com.thejoeun.practice1.boilerplate1.service;

import com.thejoeun.practice1.boilerplate1.model.dto.MemberResponseDto;
import com.thejoeun.practice1.boilerplate1.repository.MemberRepository;
import com.thejoeun.practice1.boilerplate1.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

}
