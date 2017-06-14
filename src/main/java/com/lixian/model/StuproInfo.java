package com.lixian.model;
/**
 * 学生登录信息展示类
 * @author Administrator
 *
 */
public class StuproInfo {
	private String id;
	private String stuname;
	private String workname;
	private String kechengname;
	private String filepath;
	private String createtime;
	private Integer score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public String getKechengname() {
		return kechengname;
	}
	public void setKechengname(String kechengname) {
		this.kechengname = kechengname;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
}
