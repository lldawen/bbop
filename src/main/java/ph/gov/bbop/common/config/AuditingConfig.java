package ph.gov.bbop.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "bbopAuditorAware")
public class AuditingConfig {

    @Bean
    public BbopAuditorAwareImpl bbopAuditorAware() {
        return new BbopAuditorAwareImpl();
    }
}
