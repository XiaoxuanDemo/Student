package com.lixian.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lixian.mapper.StudentMapper;
import com.lixian.model.Student;
import com.lixian.service.StudentService;

@Service("StudentService")
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentMapper studao;
	/**
	 * Ìí¼ÓÑ§Éú
	 */
	public boolean addUser(Student stu) {
		// TODO Auto-generated method stub
		int i = studao.insert(stu);
		if(i>0){
			return true;
		}
		return false;
	}

}
