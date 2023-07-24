package com.thejoeun.practice1.boilerplate1.model;

import com.thejoeun.practice1.boilerplate1.model.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.apache.ibatis.annotations.Mapper;

@Entity
@Mapper
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    //    @Column(nullable = false)
    private String password;

    //    @Column(nullable = false)
    private String nickname;

    private String provider;

    @Column(length = 2000)
    private String accessToken;

    private Long accessTokenExpireIn;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    public Member update(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
        return this;
    }

    public Member update(
//            String name,
            String accessToken,
            Long accessTokenExpireIn,
            String email
    ) {
//        this.name = name;
        this.accessToken = accessToken;
        this.accessTokenExpireIn = accessTokenExpireIn;
        this.email = email;
        return this;
    }
}
