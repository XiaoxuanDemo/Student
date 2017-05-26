package com.lixian.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;
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
	 * @return token  ÿ���������token token=id+���� ��MD5
	 */
	//{"message":"��¼�ɹ�","code":200,"data":{"student":{"stuname":"��׳ʵ4","id":"A2013121004","classname":"�׶�԰���3","password":"E10ADC3949BA59ABBE56E057F20F883E"},"token":"B650F8AD7D82404E65E4E5A0440B943C"}}
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
		StudentInfo login = stuService.login(stu);
		if(login==null){
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û����������벻��ȷ");
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("��¼�ɹ�");
		Map m=new HashMap();
		m.put("student", login);
		m.put("token", MD5Tools.MD5(login.getId()+login.getPassword()));
		ret.setData(m);
		return ret;
	}
	/**
	 * ��ȡ�û�չʾ��Ϣ
	 * @param stuid
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/showStudentMessage",method=RequestMethod.POST)
	@ResponseBody
	public Object showUserMessage(String stuid,String token){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Map m=new HashMap();
		if(checkUser(stuid,token)){
			int commitNum = stuService.getStudentCommitNum(stuid);
			int homeWorkNum = stuService.getStudentHomeWorkNum(stuid);
			int kechengNum = stuService.getStudentKechenNum(stuid);
			m.put("commitNum", commitNum);
			m.put("homWorkNum", homeWorkNum);
			m.put("kechengNum",kechengNum);
			ret.setCode(ret.SUCCESS);
			ret.setData(m);
			ret.setMessage("��ѯ�ɹ�");
			return ret;
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û��Ƿ�");
			return ret;
		}
	}
	/**
	 * У���û��Ƿ�Ϸ�
	 * @param stuid
	 * @param token
	 * @return
	 */
	private boolean checkUser(String stuid, String token) {
		// TODO Auto-generated method stub
		Student stu = new Student();
		stu.setId(stuid);
		stu= stuService.getStudent(stu);
		return token.equals(MD5Tools.MD5(stu.getId()+stu.getPassword()));
	}
	/**
	 * ѧ��ѡ��
	 * @param stuid ѧ��
	 * @param token token
	 * @param kechengid �γ̺�
	 * @return
	 */
	@RequestMapping(value="/checkKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object choseKecheng(String stuid,String token,String kechengid){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)||Utils.isParmNull(kechengid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(stuid, token)){
			if(stuService.choseKecheng(stuid, kechengid)){
				ret.setMessage("ѡ�γɹ�");
				ret.setCode(ret.SUCCESS);
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("���ݿ��쳣");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
	}
	/**
	 * ɾ��ѡ��
	 * @param stuid
	 * @param token
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteKecheng(String stuid,String token,String id){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)||Utils.isParmNull(id)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(stuid, token)){
			if(stuService.deleteKecheng(id)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("ɾ���ɹ�");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("���ݿ��쳣");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
	}
}
