package com.lixian.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	
	

}
