package com.businessbook.platform.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogToConsole implements Log{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

	@Override
	public void info(String message) {
		log("APP-INFO", message);
	}

	@Override
	public void warning(String message) {
		log("APP-WARN", message);
	}

	@Override
	public void error(String message) {
		log("APP-ERROR", message);
	}

	@Override
	public void error(Throwable err, String message) {
		log("APP-ERROR", message);
	}

	@Override
	public void trace(String message) {
		log("APP-TRACE", message);
	}
	
	private void log(String level, String message) {
		StringBuilder builder = new StringBuilder(sdf.format(new Date()));
		builder.append(" [").append(level).append("] ").append(message);
		System.out.println(builder.toString());
	}

}
