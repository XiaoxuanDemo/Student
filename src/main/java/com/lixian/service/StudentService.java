package com.lixian.service;

import java.util.List;

import com.lixian.model.Student;

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
	public Student login(Student stu);
}
