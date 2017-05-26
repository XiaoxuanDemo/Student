package com.lixian.service;

import java.util.List;

import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
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
	/**
	 * ���ҽ�ʦ
	 * @param tea
	 * @return
	 */
	List<Teacher> searchTeacher(String keyword);
	/**
	 * ��ӿγ�
	 * @param kc
	 * @return
	 */
	boolean addKecheng(Kecheng kc);
	/**
	 * ����γ�
	 * @param kcs
	 */
	void improtKecheng(List<Kecheng> kcs);
	/**
	 * �����ҵ
	 */
	public boolean addHomeWork(Homework work);
	/**
	 * ��ȡ��ҵ
	 * @param id
	 * @return
	 */
	public Homework getHomework(String id);
	/**
	 * ������ҵ 
	 * @param work
	 * @return
	 */
	public boolean updateHomeWork(Homework work);
	/**
	 * ɾ����ҵ
	 * @param workid
	 * @return
	 */
	public boolean deleteHomeWork(String workid);
}
