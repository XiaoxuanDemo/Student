package com.lixian.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lixian.model.Student;
import com.lixian.service.StudentService;
import com.lixian.tools.MD5Tools;
import com.lixian.tools.MessageReturn;
import com.lixian.tools.Utils;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource
	private StudentService stuService;
	/**
	 * �û���¼
	 * @param stuid ѧ��id
	 * @param password ����
	 * @return token  ÿ���������token
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object Login(String stuid,String password){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Student stu = new Student();
		stu.setPassword(MD5Tools.MD5(password));
		stu.setId(stuid);
		Student login = stuService.login(stu);
		if(login==null){
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û�δ�ҵ�");
		}
		if(login==stu){
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�������");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("��¼�ɹ�");
		Map m=new HashMap();
		m.put("student", login);
		m.put("token", MD5Tools.MD5(login.getStunam()+login.getPassword()));
		ret.setData(m);
		return ret;
	}
	
}
