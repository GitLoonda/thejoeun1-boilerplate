package com.thejoeun.practice1.boilerplate1.controller;

import com.thejoeun.practice1.boilerplate1.model.dto.MemberResponseDto;
import com.thejoeun.practice1.boilerplate1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        System.out.println("nickname : " + myInfoBySecurity.getNickname());

        return ResponseEntity.ok(myInfoBySecurity);
    }
}
