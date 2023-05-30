package com.ivoiceafrica.ivoiceafrica.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.ivoiceafrica.ivoiceafrica.models.LoginSuccessHandler;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // Method Security
public class SecurityConfig {

	@Value("${spring.websecurity.debug:false}")
	boolean webSecurityDebug;

	@Value("${upload.path}")
	String uploadDir;

	@Value("${flutterwave.baseurl}")
	String baseUrl;

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Bean
	public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(customUserDetailService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/login", "/landingpage", "/mainpage", "/signin", "/client-signup",
						"/client-profile-setup", "/client-upload-pic", "/freelancer-signup",
						"/freelancer-profile-setup", "/freelancer-profile-2", "/freelancer-profile-3",
						"/freelancer-profile-4", "/freelancer/signup/save", "/freelancer/detail/save",
						"/freelancer/skill/save", "/freelancer/profilepicture/save", "/client/signup/save",
						"/client/personalDetail/save", "/client/profilePicture/save", "/get-environment-profile",
						"/get-upload-path")
				.permitAll().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/signin").failureUrl("/signin?error=true").usernameParameter("username")
				.passwordParameter("password")
				// .defaultSuccessUrl("/admin-home")
				.successHandler(loginSuccessHandler).permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index")
				.invalidateHttpSession(true).permitAll().deleteCookies("JSESSIONID").and().exceptionHandling().and()
				.csrf().and().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.disable().headers().frameOptions().sameOrigin();

		return http.build();
	}

//	This second filter chain will secure the static resources without reading the SecurityContext from the session.
	@Bean
	SecurityFilterChain resources(HttpSecurity http) throws Exception {
		http.requestMatchers((matchers) -> matchers.antMatchers("/static/**", "/assets/**", "/@popperjs/**",
				"/bootstrap/**", "/CSS/**", "/fonts/**", "/icons/**", "/images/**", "/JS/**", uploadDir + "/**"))
				.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll()).requestCache().disable()
				.securityContext().disable().sessionManagement().disable();

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("*", "https://checkout-v3-ui-prod.f4b-flutterwave.com/",
				"https://api.ravepay.co/v3/checkout/initialize"));
		config.setAllowedHeaders(
				Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Cache-Control", "Content-Type"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		// configuration.setAllowedOrigins(ImmutableList.of("http://localhost:8080","http://localhost:8084"));
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(
				Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Cache-Control", "Content-Type"));
		configuration.setMaxAge(3600L);
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
