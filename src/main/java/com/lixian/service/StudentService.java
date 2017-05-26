package com.lixian.service;

import java.util.List;

import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;

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
	/**
	 * ѧ����¼
	 * @param stu
	 * @return
	 */
	public StudentInfo login(Student stu);
	/**
	 * ��ȡѧ��
	 * @param stu
	 * @return
	 */
	public Student getStudent(Student stu);
	/**
	 * ��ȡѧ����ҵ��
	 * @param stuid
	 * @return
	 */
	public int getStudentHomeWorkNum(String stuid);
	/**
	 * ��ȡѧ���ύ��ҵ��
	 * @param stuid
	 * @return
	 */
	public int getStudentCommitNum(String stuid);
	/**
	 * ��ȡѧ���γ���
	 * @param stuid
	 * @return
	 */
	public int getStudentKechenNum(String stuid);
	/**
	 * ѧ��ѡ��
	 * @param stuid
	 * @return
	 */
	public boolean choseKecheng(String stuid,String kechengid);
	/**
	 * ɾ��ѧ��ѡ��
	 * @param kechengid
	 * @return
	 */
	public boolean deleteKecheng(String kechengid);
}
