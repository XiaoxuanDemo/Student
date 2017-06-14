package com.lixian.model;
/**
 * 课程信息详细类
 * @author Administrator
 *
 */
public class KechengInfo {
	private String id;//课程id
	private String name;//课程名
	private String classname;//班级名
	private String teachername;//教师名
	private Integer stunum;//已选人数
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
