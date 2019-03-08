package com.businessbook.platform.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class BasicAuthenticationHttpInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("In BasicAuthenticationHttpInterceptor");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	

}
