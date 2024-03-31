package com.todo.todoappmanagement.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
	/**
	 * This method is used to print the simple info message.
	 * 
	 * @param logger  the Logger
	 * @param message the message
	 */
	public static void infoSimple(Logger logger, String message) {
		logger.info(message);
	}


	/**
	 * This method is used to print the debug message.
	 * 
	 * @param logger  the Logger
	 * @param message the message
	 */
	public static void debug(Logger logger, String message) {
		logger.debug(message);
	}

	/**
	 * This method is used to print the error message.
	 * 
	 * @param logger  the Logger
	 * @param message the message
	 */
	public static void error(Logger logger, String message) {
		logger.error(message);
	}

	/**
	 * This method is used to print the error message.
	 * 
	 * @param logger  the Logger
	 * @param message the message
	 */
	public static void logError(Logger logger, String message) {
		logger.error(message);
	}

	/**
	 * Log error.
	 *
	 * @param logger the logger
	 * @param strMsg the str msg
	 * @param t      the t
	 */
	public static void logError(Logger logger, String strMsg, Throwable t) {
		logger.log(Level.ERROR, strMsg, t);
	}
}
