package com.lixian.service;

import java.util.List;

import com.lixian.model.Student;

public interface StudentService {
	/**
	 * ��ӵ���ѧ��
	 * @param stu
	 */
	public boolean addUser(Student stu);
	/**
	 * ����ѧ��
	 * @param stus
	 */
	public void importUser(List<Student> stus);
	public Student login(Student stu);
}
