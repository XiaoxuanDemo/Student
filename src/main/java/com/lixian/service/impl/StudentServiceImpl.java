package com.lixian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void importUser(List<Student> stus) {
		// TODO Auto-generated method stub
		for (int i = 0; i < stus.size(); i++) {
			Student stu = stus.get(i);
			int insert = studao.insert(stu);
			if(insert>0){
				
			}else{
				throw new RuntimeException();
			}
		}
	}
	@Override
	public Student login(Student stu) {
		// TODO Auto-generated method stub
		Student key = studao.selectByPrimaryKey(stu.getId());
		if(key==null){
			return stu;
		}
		if(stu.getPassword().equals(key.getPassword())){
			return key;
		}
		return null;
	}

}
