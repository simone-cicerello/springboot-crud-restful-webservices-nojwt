package com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfigs {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private String jwtTokenValidity;

    @Value("${jwt.token.uri}")
    private String jwtTokenUri;

    @Value("${auth.username}")
    private String authUsername;

    @Value("${auth.password}")
    private String authPassword;
}
