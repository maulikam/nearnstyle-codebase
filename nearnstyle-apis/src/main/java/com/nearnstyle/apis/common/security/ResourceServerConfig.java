package com.nearnstyle.apis.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //                .antMatchers("/rest/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/api/otp/**").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/salons/salon/**").permitAll()
                .antMatchers("/api/mobile/**").permitAll()
                .antMatchers("/api/user/forgotpassword/**").permitAll()
                .antMatchers("/api/**").access("#oauth2.hasScope('write')");
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public NearNStyleSecurityUser applicationUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        OAuth2Authentication authentication = (OAuth2Authentication) securityContext.getAuthentication();
        AuthenticationUser principal = (AuthenticationUser) authentication.getPrincipal();
        return principal.getUser();
    }
}
