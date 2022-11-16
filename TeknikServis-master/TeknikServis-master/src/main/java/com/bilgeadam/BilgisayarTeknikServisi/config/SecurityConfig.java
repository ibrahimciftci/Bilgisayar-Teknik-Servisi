package com.bilgeadam.BilgisayarTeknikServisi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private AuthenticationFailureHandler failure;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {

		// post yapabilmek için csrf disable
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/index").permitAll();
		http.authorizeRequests().antMatchers("/booking/**").hasRole("USER");
		http.authorizeRequests().antMatchers("/component/**").authenticated();
		http.authorizeRequests().antMatchers("/sale/sellproduct/**").hasRole("ADMIN");

		http.authorizeRequests().antMatchers("/bookinglist").hasRole("ADMIN");

		http.authorizeRequests().antMatchers("/proposal").hasRole("USER");
		http.authorizeRequests().antMatchers("/giveProposal").hasRole("USER");
		http.authorizeRequests().antMatchers("/verifyProposal/**").hasRole("ADMIN");

		http.authorizeRequests().antMatchers("/sale").hasRole("USER");
		http.authorizeRequests().antMatchers("/basket").hasRole("USER");

		http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests().and().logout().logoutSuccessUrl("/index");
		http.authorizeRequests().and().formLogin().loginPage("/systemlogin");
		http.authorizeRequests().and().formLogin().failureHandler(failure);
//		http.authorizeRequests().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/register");
		return http.build();
	}

//	public AccessDeniedHandler accessDeniedHandler() {
//		return new CustomAccessDeniedHandler();
//	}
}
