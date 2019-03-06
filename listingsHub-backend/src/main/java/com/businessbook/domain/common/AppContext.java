package com.businessbook.domain.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.businessbook.domain.users.model.User;
import com.businessbook.platform.logging.Log;
import com.businessbook.platform.logging.LogToConsole;

public class AppContext {
	private static Log log = new LogToConsole();
	
	/**
	 * Get authenticated app user from context
	 * @return
	 */
	public static User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * Get standard Log implementation
	 * @return
	 */
	public static Log log() {
		return log;
	}
}
