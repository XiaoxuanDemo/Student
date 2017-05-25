package com.lixian.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lixian.model.Kecheng;
import com.lixian.model.Teacher;
import com.lixian.service.TeacherService;
import com.lixian.tools.MD5Tools;
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
		tea.setPassword(MD5Tools.MD5(password));
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
	
	/**
	 * ���½�ʦ�û���Ϣ
	 * @param teacherid ��ʦ���
	 * @param email �����ʼ�
	 * @param tel �绰
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object updateTeacherInfo(String teacherid,String email,String tel){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("��ʦidΪ��");
			return ret;
		}
		Teacher tea = teaService.getTeacher(teacherid);
		if (tea!=null) {
			if(!Utils.isParmNull(email)){
				tea.setEmail(email);
			}
			if(!Utils.isParmNull(tel)){
				tea.setTelphone(tel);
			}
			ret.setCode(ret.SUCCESS);
			ret.setMessage("���³ɹ�");
			return ret;
		}else{
			ret.setCode(ret.DBERROR);
			ret.setMessage("Ϊ�ҵ��ý�ʦ");
			return ret;
		}
		
	}
	/**
	 * ��ӿγ�
	 * @param name
	 * @param teacherid
	 * @param classid
	 * @param stunum
	 * @return
	 */
	@RequestMapping(value="/addKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object addKeChen(String name,String teacherid,String classid,Integer stunum){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(name)||Utils.isParmNull(teacherid)||Utils.isParmNull(classid)||stunum==null||stunum<0){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Kecheng kc = new Kecheng();
		kc.setClassid(classid);
		kc.setTeahcerid(teacherid);
		kc.setId(UUID.randomUUID().toString());
		kc.setStunum(stunum);
		kc.setName(name);
		if(teaService.addKecheng(kc)){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("��ӳɹ�");
			return ret;
		}
		ret.setCode(ret.DBERROR);
		ret.setMessage("���ݿ��쳣");
		return ret;
	}
	
}
