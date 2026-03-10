package com.rex.boilerplate.springboot.security.jwt;

import com.rex.boilerplate.springboot.security.service.UserDetailsServiceImpl;
import com.rex.boilerplate.springboot.security.utils.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

//skip auth methods
import org.springframework.util.AntPathMatcher;
import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenManager jwtTokenManager;

	private final UserDetailsServiceImpl userDetailsService;
	private static final List<String> WHITELIST = List.of(
    "/register",
    "/login",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-ui.html",
    "/actuator/**",
    "/api/beneficiaries",
    "/api/beneficiaries/**"
    );
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // if any pattern matches the request URI, skip JWT filtering
        return WHITELIST.stream()
        .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final String header = request.getHeader(SecurityConstants.HEADER_STRING);

		String username = null;
		String authToken = null;
		if (Objects.nonNull(header) && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

			authToken = header.replace(SecurityConstants.TOKEN_PREFIX, Strings.EMPTY);

			log.info("Auth Token ", authToken);

			try {
				username = jwtTokenManager.getUsernameFromToken(authToken);
			}
			catch (Exception e) {
				log.error("Authentication Exception : {}", e.getMessage());
				chain.doFilter(request, response);
				return;
			}
		}

		final SecurityContext securityContext = SecurityContextHolder.getContext();

		final boolean canBeStartTokenValidation = Objects.nonNull(username) && Objects.isNull(securityContext.getAuthentication());

		if (!canBeStartTokenValidation) {
			chain.doFilter(request, response);
			return;
		}

		final UserDetails user = userDetailsService.loadUserByUsername(username);
		final boolean validToken = jwtTokenManager.validateToken(authToken, user.getUsername());

		if (!validToken) {
			chain.doFilter(request, response);
			return;
		}

		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		securityContext.setAuthentication(authentication);

		log.info("Authentication successful. Logged in username : {} ", username);

		chain.doFilter(request, response);
	}
}
