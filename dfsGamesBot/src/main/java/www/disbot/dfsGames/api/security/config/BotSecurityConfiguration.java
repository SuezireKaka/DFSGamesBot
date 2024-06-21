package www.disbot.dfsGames.api.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import www.disbot.dfsGames.sys.context.ApiRequestInfo;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class BotSecurityConfiguration implements WebMvcConfigurer {
	@Autowired
	private TokenExaminater tokenResolver;
	
	@Autowired
	private CustomAuthenticationEntryPoint point;
	
	@Autowired
	private CustomAccessDeniedHandler denyHandler;
	
	@Autowired
	private ApiRequestInfo requestInfo;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		// configuration
		http.httpBasic(HttpBasicConfigurer::disable)
				.csrf(CsrfConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/*/anonymous/**").permitAll())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .exceptionHandling(ex -> ex.accessDeniedHandler(denyHandler))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new DiscordAuthenticationFilter(tokenResolver), RequestCacheAwareFilter.class);
		//.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://%s:%d"
				.formatted(requestInfo.getSourceHost(), requestInfo.getSourcePort())));
		configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(
				List.of("Content-Type", "x-auth-token", "Access-Control-Allow-Origin", "Cache-control", "X-PINGOTHER"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
