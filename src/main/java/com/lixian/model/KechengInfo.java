package com.lixian.model;
/**
 * �γ���Ϣ��ϸ��
 * @author Administrator
 *
 */
public class KechengInfo {
	private String id;//�γ�id
	private String name;//�γ���
	private String classname;//�༶��
	private String teachername;//��ʦ��
	private Integer stunum;//��ѡ����
	private Integer maxnum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public Integer getStunum() {
		return stunum;
	}
	public void setStunum(Integer stunum) {
		this.stunum = stunum;
	}
	public Integer getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}
	
	
}
