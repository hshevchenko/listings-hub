package com.businessbook.platform.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingHttpRequestInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getMethod() + ": " + request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
