package com.lixian.tools;

public class MessageReturn {
	public static final int PARMISNULL=-1;
	public static final int SUCCESS=200;
	public static final int DBERROR=-2;
	private String message;
	private int code;
	private Object data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
