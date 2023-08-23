package com.nearnstyle.apis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "NearNStyle API", version = "v1", description = "NearNStyle v1 APIs"))
@EnableAsync
public class NearNStyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NearNStyleApplication.class, args);
    }

    @Bean
    public TokenStore tokenStore(@Autowired DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Configuration
    public class MyConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");
        }
    }
}
