package com.ivoiceafrica.ivoiceafrica.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ivoiceafrica.ivoiceafrica.models.LoginSuccessHandler;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) //Method Security
public class SecurityConfig  {
	
	@Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;
	
	@Autowired
    CustomUserDetailService customUserDetailService;
	
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;
	
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(customUserDetailService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/","/login","/landingpage","/mainpage","/signin","/client-signup","/client-profile-setup","/client-upload-pic",
					"/freelancer-signup","/freelancer-profile-setup","/freelancer-profile-2",
					"/freelancer-profile-3","/freelancer-profile-4","/freelancer/signup/save",
					"/freelancer/detail/save", "/freelancer/skill/save","/freelancer/profilepicture/save",
					"/client/signup/save","/client/personalDetail/save","/client/profilePicture/save").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/signin")
				.failureUrl("/signin?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
	//			.defaultSuccessUrl("/admin-home")
				.successHandler(loginSuccessHandler)
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/index")
				.invalidateHttpSession(true)
				.permitAll()
			.deleteCookies("JSESSIONID")
			.and()
			.exceptionHandling()
			.and()
			.csrf()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.disable()
			.headers()
			.frameOptions()
			.sameOrigin();

	    return http.build();
	}
	
	
//	This second filter chain will secure the static resources without reading the SecurityContext from the session.
	@Bean 
	@Order(0)
	SecurityFilterChain resources(HttpSecurity http) throws Exception {
	    http
	        .requestMatchers((matchers) -> matchers.antMatchers("/static/**","/assets/**", "/@popperjs/**", "/bootstrap/**", "/CSS/**", 
					"/fonts/**", "/icons/**", "/images/**","/JS/**"))
	        .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
	        .requestCache().disable()
	        .securityContext().disable()
	        .sessionManagement().disable();

	    return http.build();
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
//	web.ignoring() means that Spring Security cannot provide any security headers or other protective
//	measures on those endpoints. Instead, using permitAll allows Spring Security to write headers and otherwise secure the 
//	request without requiring authorization. This is why permitAll is recommended. The warning message is intended to alert 
//	you to the tradeoff
	
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		
//	    return (web) -> web.debug(webSecurityDebug)
//	      .ignoring()
//	      .antMatchers("/static/**","/assets/**", "/@popperjs/**", "/bootstrap/**", "/CSS/**", 
//					"/fonts/**", "/icons/**", "/images/**","/JS/**","/core/**");
//	}
	
}
