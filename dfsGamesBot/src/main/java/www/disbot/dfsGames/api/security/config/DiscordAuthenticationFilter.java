package www.disbot.dfsGames.api.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DiscordAuthenticationFilter extends OncePerRequestFilter {
	private final TokenExaminater examinator;

	@Override
	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		log.info("토큰 추출중...");
		
		String token = examinator.resolveToken(servletRequest);
		
		log.info("토큰 추출 완료".formatted(token));
		log.info("토큰 분석중...");

		if (token != null && examinator.validateToken(token)) {
			Authentication authentication = examinator.getAuthentication(token);
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			
			Authentication savedAuthentication = SecurityContextHolder.getContext().getAuthentication();

			log.info("토큰 확인 완료".formatted(savedAuthentication));
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
}
