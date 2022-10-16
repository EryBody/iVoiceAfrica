//package com.ivoiceafrica.ivoiceafrica.configuration;
//
//public class SecurityConfigDeprecated {
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	
//		http
//				.authorizeRequests()
//				.antMatchers("/","/signup").permitAll()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().authenticated()
//				.and()
//				.formLogin()
//					.loginPage("/login")
//					.failureUrl("/login?error=true")
//					.usernameParameter("username")
//					.passwordParameter("password")
////					.defaultSuccessUrl("/admin-home")
//					.successHandler(loginSuccessHandler)
//					.permitAll()
//				.and()
//					.logout()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//					.logoutSuccessUrl("/login")
//					.invalidateHttpSession(true)
//					.permitAll()
//				.deleteCookies("JSESSIONID")
//				.and()
//				.exceptionHandling()
//				.and()
//				.csrf()
//				.disable();
//	}
//	
//	   
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth
//			.userDetailsService(customUserDetailService);
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//
//		web.ignoring().antMatchers("/static/**","/assets/**", "/@popperjs/**", "/bootstrap/**", "/CSS/**", 
//								"/fonts/**", "/icons/**", "/images/**","/JS/**","/core/**");
//	}
//
//	
//}
