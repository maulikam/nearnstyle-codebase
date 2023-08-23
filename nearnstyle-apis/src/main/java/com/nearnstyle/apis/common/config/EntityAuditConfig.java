package com.nearnstyle.apis.common.config;

import com.nearnstyle.apis.common.security.AuthenticationUser;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
 
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

@EnableJpaAuditing(auditorAwareRef = "createAuditorProvider")
@Configuration
public class EntityAuditConfig {

    @Bean
    public AuditorAware<Long> createAuditorProvider() {
        return new SecurityAuditor();
    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }

    public class SecurityAuditor implements AuditorAware<Long> {

        @Autowired
        private HttpServletRequest request;

        @Override
        public Optional<Long> getCurrentAuditor() {
            Long id = null;
            SecurityContext securityContext = SecurityContextHolder.getContext();

            try {

                if (securityContext.getAuthentication() != null && (securityContext.getAuthentication() instanceof OAuth2Authentication)) {

                    OAuth2Authentication authentication = (OAuth2Authentication) securityContext.getAuthentication();
                    AuthenticationUser principal = (AuthenticationUser) authentication.getPrincipal();
                    if (principal != null && principal.getUser().getId() != null) {
                        id = principal.getUser().getId();
                    } else {
                        id = -1L;
                    }

                    return Optional.ofNullable(id);
                } else {
                    return Optional.ofNullable(-1L);
                }

            } catch (BeanCreationException beanCreationException) {
                return Optional.ofNullable(-1L);
            }

        }
    }

}
