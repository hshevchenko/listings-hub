package com.businessbook.config.security;

import static com.businessbook.domain.common.WebServicesConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.businessbook.domain.users.services.UsersService;
import com.businessbook.platform.security.jwt.JWTAuthenticationFilter;
import com.businessbook.platform.security.jwt.JWTAuthorizationFilter;

/*@Configuration
@EnableWebSecurity*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UsersService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, USERS_SIGN_UP_URI).permitAll()
        .antMatchers(HttpMethod.GET, ALL_URI).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
        // this disables session creation on Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			
		
		/*http.authorizeRequests().antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
        	.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
        	// this disables session creation on Spring Security
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        	
        		*/
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
