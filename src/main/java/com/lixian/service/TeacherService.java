package com.lixian.service;

import java.util.List;

import com.lixian.model.Teacher;

public interface TeacherService {
	/**
	 * ��ӽ�ʦ
	 * @param teacher
	 * @return
	 */
	boolean addTeacher(Teacher teacher);
	/**
	 * �����ʦ
	 * @param list
	 * @return
	 */
	boolean importTeacher(List<Teacher> list);
	/**
	 * ͨ����ʦID���ҽ�ʦ
	 * @param id
	 * @return
	 */
	Teacher getTeacher(String id);
	List<Teacher> searchTeacher(Teacher tea);
}
