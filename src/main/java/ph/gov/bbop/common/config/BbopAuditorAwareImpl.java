package ph.gov.bbop.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ph.gov.bbop.common.CommonConstants;

import java.util.Optional;

@Slf4j
public class BbopAuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("BbopAuditorAwareImpl | getCurrentAuditor | authentication: " + authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(CommonConstants.SYSTEM);
        }
        return Optional.of((String) authentication.getPrincipal());
    }
}
