package com.businessbook.platform.security;

import static com.businessbook.domain.common.WebServicesConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.businessbook.domain.users.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	private UsersService usersService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//All requests are permitted except account/listings maintenance 
		http
		.csrf().disable()
		.cors().and()		
		.authorizeRequests()			
			.antMatchers("/account/**").authenticated().and().httpBasic()				
		.and().authorizeRequests()
			.anyRequest().permitAll()
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.formLogin().successHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				response.setStatus(HttpStatus.OK.value());
			}
		}).failureHandler(new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				
			}
		})
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(new AuthenticationEntryPoint());
		
		//All request needs to be authorized except user registration and login
		/*http
		.csrf().disable()
		.cors().and()		
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, USERS_SIGN_UP_URI).permitAll()
			.antMatchers(HttpMethod.POST, USERS_LOGIN_URI).permitAll()
			.anyRequest().authenticated().and().httpBasic()
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.formLogin().successHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				response.setStatus(HttpStatus.OK.value());
			}
		}).failureHandler(new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				
			}
		})
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(new AuthenticationEntryPoint()); */
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService);		
	}	
	
}
