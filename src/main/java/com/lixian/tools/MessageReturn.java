package com.lixian.tools;

public class MessageReturn {
	//����Ϊ��
	public static final int PARMISNULL=-1;
	//�ɹ�
	public static final int SUCCESS=200;
	//���ݿ��쳣
	public static final int DBERROR=-2;
	//IO�쳣
	public static final int IOERROR=-3;
	//�����쳣
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
