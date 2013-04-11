package org.cn.pilot.itemmgr.utils;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:35:25 PM ] -->
 */
public class AppException extends RuntimeException {

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

}
