package com.businessbook.platform.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.businessbook.domain.common.AppContext;
import com.businessbook.domain.users.model.User;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.businessbook.domain.common.WebServicesConstants.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AppContext.log().trace("Attempt to authenticate HTTP request");
		
		try {
			User creds = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);
			AppContext.log().trace("User from request: " + creds);
			
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		AppContext.log().trace("Successful authentication of HTTP request: user=" + ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername());
		String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
}
