package com.lixian.tools;

public class MessageReturn {
	//参数为空
	public static final int PARMISNULL=-1;
	//成功
	public static final int SUCCESS=200;
	//数据库异常
	public static final int DBERROR=-2;
	//IO异常
	public static final int IOERROR=-3;
	//参数异常
	public static final int PARMERROR=-4;
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
