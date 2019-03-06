package com.businessbook.platform.security.jwt;

import static com.businessbook.domain.common.WebServicesConstants.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.businessbook.domain.common.AppContext;
import com.businessbook.domain.users.model.User;
import com.businessbook.domain.users.services.UsersService;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
		
	private UsersService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UsersService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}
	
	@Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.        	
        	String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {                     	
            	User appUser = this.userDetailsService.findByUsername(user);
            	if(appUser != null) {
            		AppContext.log().trace("JWTAuthorizationFilter: User authorized ["+ appUser.getUsername() + ", " +appUser.getId()+"]");
            		return  new UsernamePasswordAuthenticationToken(appUser, null, new ArrayList<>());   
            	}            	           
            }
            return null;
        }
        return null;
    }

}
