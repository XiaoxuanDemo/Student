package com.lixian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.lixian.mapper.HomeworkMapper;
import com.lixian.mapper.KechengMapper;
import com.lixian.mapper.TeacherMapper;
import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.StudentInfo;
import com.lixian.model.Teacher;
import com.lixian.service.TeacherService;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService{
	@Resource
	private TeacherMapper teadao;
	@Resource
	private KechengMapper kcdao;
	@Resource
	private HomeworkMapper workdao;
	public boolean addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return true;
	}
	@Transactional
	public boolean importTeacher(List<Teacher> list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			Teacher teacher = list.get(i);
			int insert = teadao.insert(teacher);
			if(insert>0){
				
			}else{
				throw new RuntimeException();
			}
		}
		return true;
	}
	public Teacher getTeacher(String id) {
		// TODO Auto-generated method stub
		Teacher key = teadao.selectByPrimaryKey(id);
		return key;
	}
	public List<Teacher> searchTeacher(String keyword) {
		// TODO Auto-generated method stub
		return teadao.searchTeacher(keyword);
	}
	public boolean addKecheng(Kecheng kc) {
		// TODO Auto-generated method stub
		int i = kcdao.insert(kc);
		if(i>0){
			return true;
		}
		return false;
	}
	@Transactional
	public void improtKecheng(List<Kecheng> kcs) {
		// TODO Auto-generated method stub
		for (int i = 0; i < kcs.size(); i++) {
			Kecheng kc = kcs.get(i);
			int insert = kcdao.insert(kc);
			if(insert>0){
				
			}else{
				throw new RuntimeException();
			}
		}
	}
	@Override
	public boolean addHomeWork(Homework work) {
		// TODO Auto-generated method stub
		int i = workdao.insert(work);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public Homework getHomework(String id) {
		// TODO Auto-generated method stub
		return workdao.selectByPrimaryKey(id);
	}
	@Override
	public boolean updateHomeWork(Homework work) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteHomeWork(String workid) {
		// TODO Auto-generated method stub
		int i = workdao.deleteByPrimaryKey(workid);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public List<StudentInfo> getAllStudent(int pageSize, int pageNum, String classid, String kechengid,
			String teacherid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<StudentInfo> list = teadao.searchStudent(teacherid, classid, kechengid);
		return list;
	}
	

	
	

}
