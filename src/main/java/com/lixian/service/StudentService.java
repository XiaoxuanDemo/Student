package com.lixian.service;

import java.util.List;

import com.lixian.model.HomeWorkInfo;
import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.KechengInfo;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;
import com.lixian.model.StuproInfo;

public interface StudentService {
	/**
	 * 添加单个学生
	 * @param stu
	 */
	public boolean addUser(Student stu);
	/**
	 * 导入学生
	 * @param stus
	 */
	public void importUser(List<Student> stus);
	/**
	 * 学生登录
	 * @param stu
	 * @return
	 */
	public StudentInfo login(Student stu);
	/**
	 * 获取学生
	 * @param stu
	 * @return
	 */
	public Student getStudent(Student stu);
	/**
	 * 获取学生作业数
	 * @param stuid
	 * @return
	 */
	public int getStudentHomeWorkNum(String stuid);
	/**
	 * 获取学生提交作业数
	 * @param stuid
	 * @return
	 */
	public int getStudentCommitNum(String stuid);
	/**
	 * 获取学生课程数
	 * @param stuid
	 * @return
	 */
	public int getStudentKechenNum(String stuid);
	/**
	 * 学生选课
	 * @param stuid
	 * @return
	 */
	public boolean choseKecheng(String stuid,String kechengid);
	/**
	 * 删除学生选课
	 * @param kechengid
	 * @return
	 */
	public boolean deleteKecheng(String kechengid,String stuid);
	/**
	 * 获取学生可选课程
	 * @param stuid
	 * @return
	 */
	public List<KechengInfo> getStudentCanKecheng(String stuid);
	
	public List<KechengInfo> getStudentHaveKecheng(String stuid);
	/**
	 * 添加作业
	 * @param stuid
	 * @param filepath
	 * @param kechengid
	 * @return
	 */
	public boolean commitHomeWork(String stuid,String filepath,String kechengid);
	/**
	 * 显示学生作业内容
	 * @param stuid
	 * @return
	 */
	public List<HomeWorkInfo> showHomeWork(String stuid);
	/**
	 * 显示学生作业成绩等信息
	 * @param stuid
	 * @return
	 */
	public List<StuproInfo> showStupro(String stuid);
}
