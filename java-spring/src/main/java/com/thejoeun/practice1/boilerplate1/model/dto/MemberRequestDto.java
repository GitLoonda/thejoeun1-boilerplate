package com.thejoeun.practice1.boilerplate1.model.dto;

import com.thejoeun.practice1.boilerplate1.model.Member;
import com.thejoeun.practice1.boilerplate1.model.enums.Authority;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public MemberResponseDto toMemberResponseDto(MemberRequestDto request) {
        return MemberResponseDto.builder()
                .email(email)
                .nickname(nickname)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
