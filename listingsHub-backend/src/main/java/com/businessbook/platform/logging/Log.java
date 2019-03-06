package com.businessbook.platform.logging;

public interface Log {
	/**
	 * Log message to standard output channel
	 * @param message
	 */
	void info(String message);
	
	/**
	 * Log trace message to standard output channel
	 * Used in development mode
	 * @param message
	 */
	void trace(String message);
	
	/**
	 * Log warning message to standard output channel
	 * @param message
	 */
	void warning(String message);
	
	/**
	 * Log error message to standard output channel
	 * @param message
	 */
	void error(String message);
	
	/**
	 * Log error message to standard output channel
	 * @param message
	 */
	void error(Throwable err, String message);
	
	
}
