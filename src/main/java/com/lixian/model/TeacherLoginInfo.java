package com.lixian.model;
/***
 * 教师登录展示信息类
 * @author Administrator
 *
 */
public class TeacherLoginInfo {
	private String id;//教师ID
	private String email;//电子邮件
	private String telphone;//地址
	private int classnum;//班级名字
	private int kechengnum;//授课数
	private int homeworknum;//作业数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public int getClassnum() {
		return classnum;
	}
	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}
	public int getKechengnum() {
		return kechengnum;
	}
	public void setKechengnum(int kechengnum) {
		this.kechengnum = kechengnum;
	}
	public int getHomeworknum() {
		return homeworknum;
	}
	public void setHomeworknum(int homeworknum) {
		this.homeworknum = homeworknum;
	}
	
}
