package org.cc.automate.core;


/**
 * 业务异常，在底层自行抛出，在controller层自行捕获处理
 * 
 * @author liuw
 *
 */
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1744557298665473334L;
	/**
	 * httpcode 必须
	 */
	private int status = 400;
	/**
	 *  自定义 国际化使用
	 */
	private int errorCode = 500;
	
	private String message;

	private Exception exception;

	public BusinessException(int status, int errorCode, String message, Exception exception) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		this.exception = exception;
	}

	public BusinessException(int status, int errorCode, String message) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public BusinessException(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public BusinessException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public BusinessException(String message){
		super();
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
