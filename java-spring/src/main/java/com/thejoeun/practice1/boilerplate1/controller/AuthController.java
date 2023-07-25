package com.thejoeun.practice1.boilerplate1.controller;

import com.thejoeun.practice1.boilerplate1.model.dto.JwtTokenDto;
import com.thejoeun.practice1.boilerplate1.model.dto.MemberRequestDto;
import com.thejoeun.practice1.boilerplate1.model.dto.MemberResponseDto;
import com.thejoeun.practice1.boilerplate1.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto request) {
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = authService.signup(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
                    .body(request.toMemberResponseDto(request));
        }
        return ResponseEntity.ok(memberResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody MemberRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
