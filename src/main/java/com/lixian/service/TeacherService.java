package com.lixian.service;

import java.util.List;

import com.lixian.model.Teacher;

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
	List<Teacher> searchTeacher(Teacher tea);
}
