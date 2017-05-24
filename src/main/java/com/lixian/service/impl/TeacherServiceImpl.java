package com.lixian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixian.mapper.TeacherMapper;
import com.lixian.model.Teacher;
import com.lixian.service.TeacherService;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService{
	@Resource
	private TeacherMapper teadao;
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
	public List<Teacher> searchTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
