package com.lixian.model;
/***
 * ��ʦ��¼չʾ��Ϣ��
 * @author Administrator
 *
 */
public class TeacherLoginInfo {
	private String id;//��ʦID
	private String email;//�����ʼ�
	private String telphone;//��ַ
	private int classnum;//�༶����
	private int kechengnum;//�ڿ���
	private int homeworknum;//��ҵ��
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
