package com.thejoeun.practice1.boilerplate1.config.oauth;

import com.thejoeun.practice1.boilerplate1.model.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RequiredArgsConstructor
@Component
public class OAuth2Custom2SuccessHandler implements AuthenticationSuccessHandler {
    public static final String REDIRECT_URI = "http://localhost:3000/oauth2login/callback";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    private final OAuth2CustomUserService oAuth2CustomUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String loginEmail = (String) oAuth2User.getAttributes().get("email");
        Member member = oAuth2CustomUserService.getMemberByEmail(loginEmail);
        response.sendRedirect(UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .queryParam("accessToken", member.getAccessToken())
                .queryParam("expirationTimeIn", member.getAccessTokenExpireIn())
                .queryParam("refreshToken", "refreshToken")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString());
    }
}
