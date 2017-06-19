package com.lixian.service;

import java.util.List;

import com.lixian.model.Banji;
import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Teacher;
import com.lixian.model.TeacherLoginInfo;

public interface TeacherService {
	/**
	 * 添加教师
	 * @param teacher
	 * @return
	 */
	boolean addTeacher(Teacher teacher);
	/**
	 * 导入教师
	 * @param list
	 * @return
	 */
	boolean importTeacher(List<Teacher> list);
	/**
	 * 通过教师ID查找教师
	 * @param id
	 * @return
	 */
	Teacher getTeacher(String id);
	/**
	 * 查找教师
	 * @param tea
	 * @return
	 */
	List<Teacher> searchTeacher(String keyword);
	/**
	 * 添加课程
	 * @param kc
	 * @return
	 */
	boolean addKecheng(Kecheng kc);
	/**
	 * 导入课程
	 * @param kcs
	 */
	void improtKecheng(List<Kecheng> kcs);
	/**
	 * 添加作业
	 */
	public boolean addHomeWork(Homework work);
	/**
	 * 获取作业
	 * @param id
	 * @return
	 */
	public Homework getHomework(String id);
	/**
	 * 更新作业 
	 * @param work
	 * @return
	 */
	public boolean updateHomeWork(Homework work);
	/**
	 * 删除作业
	 * @param workid
	 * @return
	 */
	public boolean deleteHomeWork(String workid);
	
	/**
	 * 根据条件查询学生
	 * @param pageSize
	 * @param pageNum
	 * @param classid
	 * @param kechengid
	 * @param teacherid
	 * @return
	 */
	public List<StudentInfo> getAllStudent(int pageSize,int pageNum,String classid,String kechengid,String teacherid);
	/**
	 * 获取教师登陆信息
	 * @param teacherid
	 * @return
	 */
	public TeacherLoginInfo getLoginInfo(String teacherid);
	/**
	 * 获取班级
	 */
	public List<Banji> getBanji();
}
