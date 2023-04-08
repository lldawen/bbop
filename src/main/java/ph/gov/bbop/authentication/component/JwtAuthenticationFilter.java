package ph.gov.bbop.authentication.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        log.info("JwtAuthenticationFilter | doFilterInternal | authorizationHeader: " + authorizationHeader);

        if (!StringUtils.startsWith(authorizationHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(7);
        final String username = jwtAuthenticationService.getUsername(token);
        log.info("JwtAuthenticationFilter | doFilterInternal | token: " + token);
        log.info("JwtAuthenticationFilter | doFilterInternal | username: " + username);

        if (!usernameExistsInSecurityContext(username)) {
            log.info("JwtAuthenticationFilter | doFilterInternal | 11111: ");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtAuthenticationService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean usernameExistsInSecurityContext(String username) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return false;
        }
        return StringUtils.equals(
                username,
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
    }
}
