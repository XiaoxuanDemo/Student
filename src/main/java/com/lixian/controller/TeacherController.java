package com.lixian.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lixian.model.Teacher;
import com.lixian.service.TeacherService;
import com.lixian.tools.MessageReturn;
import com.lixian.tools.Utils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Resource
	private TeacherService teaService;
	/**
	 * ��ӽ�ʦ
	 * @param teachername ��ʦ����
	 * @param password ����
	 * @param telphone �绰������Ϊ��
	 * @param email �ʼ���ַ������Ϊ��
	 * @return
	 */
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	@ResponseBody
	public Object addTeacher(String teachername,String password,String telphone,String email){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teachername)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Teacher tea = new Teacher();
		UUID uuid = UUID.randomUUID();
		tea.setId(uuid.toString());
		tea.setTeachername(teachername);
		tea.setPassword(password);
		if (!Utils.isParmNull(email)) {
			tea.setEmail(email);
		}
		if(!Utils.isParmNull(telphone)){
			tea.setTelphone(telphone);
		}
		if(teaService.addTeacher(tea)){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("��ӳɹ�");
			ret.setData(tea);
			return ret;
		}else {
			ret.setCode(ret.DBERROR);
			ret.setMessage("���ݿ��쳣");
			return ret;
		}
		
	}
	@RequestMapping(value="test",method=RequestMethod.POST)
	@ResponseBody
	public Object upLoadTeacher(@RequestParam("name")MultipartFile file){
		if(file==null||file.isEmpty()){
			
		}
		return null;
	}
}
