package com.cfido.snapshot.mvc;

/**
 * <pre>
 * 基础的json返回对象
 * </pre>
 * 
 * @author <a href="https://github.com/liangwj72">Alex (梁韦江)</a>
 * @date 2016年3月21日
 */
public abstract class BaseResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8606885160883846129L;

	public static final int OK = 1;

	private int code = OK;
	private String errorMsg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isSuccess() {
		return this.code == OK;
	}

}
