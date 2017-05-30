package com.lixian.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixian.mapper.KechengMapper;
import com.lixian.mapper.StudentMapper;
import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.KechengInfo;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;
import com.lixian.service.StudentService;

@Service("StudentService")
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentMapper studao;
	@Resource
	private KechengMapper kcdao;
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
	public StudentInfo login(Student stu) {
		// TODO Auto-generated method stub
		try{
			StudentInfo info = studao.getStudentInfo(stu.getId());
			if(info==null){
				return null; 
			}
			if(info.getPassword().equals(stu.getPassword())){
				return info;
			}
			return null;
		}catch(NullPointerException e){
			return null;
		}
	}
	@Override
	public Student getStudent(Student stu) {
		// TODO Auto-generated method stub
		return studao.selectByPrimaryKey(stu.getId());
	}

	@Override
	public boolean choseKecheng(String stuid,String kechengid) {
		// TODO Auto-generated method stub
		int count = kcdao.getChoseCount(kechengid);
		int k=kcdao.getStuchoseKecheng(stuid, kechengid);
		if(k>0){
			return false;
		}
		Kecheng key = kcdao.selectByPrimaryKey(kechengid);
		if(key.getStunum()<count){
			return false;
		}
		int i = studao.insertStuKc(UUID.randomUUID().toString(), stuid, kechengid);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public int getStudentHomeWorkNum(String stuid) {
		// TODO Auto-generated method stub
		return studao.getStudentHomeWorkNum(stuid);
	}
	@Override
	public int getStudentCommitNum(String stuid) {
		// TODO Auto-generated method stub
		return studao.getStudentCommitNum(stuid);
	}
	@Override
	public int getStudentKechenNum(String stuid) {
		// TODO Auto-generated method stub
		return studao.getStudentKechenNum(stuid);
	}
	@Override
	public boolean deleteKecheng(String kechengid,String stuid) {
		// TODO Auto-generated method stub
		Integer i = kcdao.deleteStuKecheng(kechengid,stuid);
		System.out.println(i);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public List<KechengInfo> getStudentCanKecheng(String stuid) {
		// TODO Auto-generated method stub
		
		return kcdao.getStuCanKecheng(stuid);
	}
	@Override
	public List<KechengInfo> getStudentHaveKecheng(String stuid) {
		// TODO Auto-generated method stub
		return kcdao.getStuHaveKecheng(stuid);
	}
	@Override
	public boolean commitHomeWork(String stuid, String filepath, String kechengid) {
		// TODO Auto-generated method stub
		return false;
	}

}
