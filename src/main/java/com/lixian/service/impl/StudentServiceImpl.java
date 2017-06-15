package com.lixian.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixian.mapper.KechengMapper;
import com.lixian.mapper.StudentMapper;
import com.lixian.mapper.StuproMapper;
import com.lixian.model.HomeWorkInfo;
import com.lixian.model.Homework;
import com.lixian.model.HomeworkData;
import com.lixian.model.Kecheng;
import com.lixian.model.KechengInfo;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;
import com.lixian.model.StuproInfo;
import com.lixian.service.StudentService;

import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;

@Service("StudentService")
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentMapper studao;
	@Resource
	private KechengMapper kcdao;
	@Resource
	private StuproMapper prodao;
	/**
	 * 添加学生
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
		long currentTimeMillis = System.currentTimeMillis();
		String time=currentTimeMillis/1000+"";
		Stupro pro = new Stupro();
		String id=UUID.randomUUID().toString();
		pro.setId(id);
		pro.setFilepath(filepath);
		pro.setScore(0);
		pro.setStuid(stuid);
		pro.setWorkid(kechengid);
		pro.setCreatetime(time);
		int i = prodao.insertSelective(pro);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public List<HomeWorkInfo> showHomeWork(String stuid) {
		// TODO Auto-generated method stub
		return studao.getHomeWork(stuid);
	}
	@Override
	public List<StuproInfo> showStupro(String stuid) {
		// TODO Auto-generated method stub
		return studao.getStuproInfo(stuid);
	}
	/**
	 * 文本查重存库
	 * @author Administrator
	 * 暂时不做 
	 */
	class SaveFile implements Runnable{
		private String path;//文件路径
		private String homeworkid;
		private String proid;//作业ID
		public SaveFile(String path,String homeworid,String proid){
			String root = System.getProperty("rootpath");
			this.path=root+path;
			this.homeworkid=homeworid;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				FileInputStream fis = new FileInputStream(new File("E:\\workspace\\MyApplication\\app\\src\\main\\java\\leoseven\\com\\stcamera\\STcameraView.java"));
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				int len=0;
				byte[] buffer=new byte[1024];
				while (-1!=(len=fis.read(buffer))) {
					baos.write(buffer,0,len);
				}
				byte[] data = baos.toByteArray();
				String string = new String(data,"UTF-8");
				String[] split = string.split("\n");
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < split.length; i++) {
					if(split[i].indexOf("}")==-1&&split[i].indexOf("{")==-1&&split[i].indexOf("*")==-1&&split[i].indexOf("import")==-1){
						HomeworkData d = new HomeworkData();
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public boolean updataPassWord(String id, String password) {
		// TODO Auto-generated method stub
		return studao.updatePassword(id, password)>0?true:false;
	}
}
