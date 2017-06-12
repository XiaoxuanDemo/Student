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

import com.lixian.model.Homework;
import com.lixian.model.Kecheng;
import com.lixian.model.StudentInfo;
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
	public Object addTeacher(String teacherid,String teachername,String password,String telphone,String email){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teachername)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Teacher tea = new Teacher();
	
		tea.setId(teacherid);
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
	 * @param token token
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object updateTeacherInfo(String teacherid,String email,String tel,String token){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("��ʦidΪ��");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("�û����Ϸ�");
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
	 * @param name �γ���
	 * @param teacherid ��ʦ���
	 * @param classid �༶���
	 * @param stunum ѧ������
	 * @param token token
	 * @return
	 */
	@RequestMapping(value="/addKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object addKeChen(String name,String teacherid,String classid,Integer stunum,String token){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(name)||Utils.isParmNull(teacherid)||Utils.isParmNull(classid)||stunum==null||stunum<0){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(teacherid, token)){
			
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
	/**
	 * ��ʦ��¼
	 * @param teacherid
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object Login(String teacherid,String password){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		Teacher tea = teaService.getTeacher(teacherid);
		if(tea==null){
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("δ�ҵ����û�");
			return ret;
		}
		if(tea.getPassword().equals(MD5Tools.MD5(password))){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("��¼�ɹ�");
			Map m=new HashMap();
			m.put("teacher", tea);
			m.put("token", MD5Tools.MD5(tea.getId()+tea.getPassword()));
			ret.setData(m);
			return ret;
		}else{
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�������");
			return ret;	
		}
		
	}
	/**
	 * У���ʦ�Ƿ�Ϸ�
	 * @param id
	 * @param token
	 * @return
	 */
	public boolean checkUser(String id,String token){
		Teacher tea = teaService.getTeacher(id);
		if(tea==null){
			return false;
		}
		if(token.equals(MD5Tools.MD5(tea.getId()+tea.getPassword()))){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ������ҵ
	 * @param teacherid ��ʦ���
	 * @param token token
	 * @param workname ��ҵ��
	 * @param content ����
	 * @param lasttime ����ύʱ�� ʱ���
	 * @param kechengid �γ̱��
	 * @return
	 */
	@RequestMapping(value="/addHomework",method=RequestMethod.POST)
	@ResponseBody
	public Object addHomeWrok(String teacherid,String token,String workname,String content,String lasttime,String kechengid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||Utils.isParmNull(workname)||Utils.isParmNull(content)||Utils.isParmNull(lasttime)||Utils.isParmNull(kechengid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
		Homework work = new Homework();
		work.setContent(content);
		work.setKechengid(kechengid);
		work.setName(workname);
		work.setLasttime(lasttime);
		work.setId(UUID.randomUUID().toString());
		if(teaService.addHomeWork(work)){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("�����ɹ�");
			return ret;
		}
		ret.setCode(ret.DBERROR);
		ret.setMessage("����ʧ��");
		return ret;
	}
	/**
	 * ������ҵ��Ϣ
	 * @param teacherid ��ʦID
	 * @param token token
	 * @param workname ��ҵ����
	 * @param content ��ҵ����
	 * @param lasttime ����ύʱ��
	 * @param kechengid �γ�id
	 * @param workid ��ҵid
	 * @return
	 */
	@RequestMapping(value="/updateHomeWork",method=RequestMethod.POST)
	@ResponseBody
	public Object upDateHomeWork(String teacherid,String token,String workname,String content,String lasttime,String kechengid,String workid){
		MessageReturn ret = new MessageReturn();
		if(checkUser(teacherid, token)){
			Homework work = teaService.getHomework(workid);
			if(work==null){
				ret.setCode(ret.RECODEISNOFOUND);
				ret.setMessage("δ�ҵ�����ҵ");
				return ret;
			}
			if(Utils.isParmNull(workname)){
				work.setName(workname);
			}
			if(Utils.isParmNull(content)){
				work.setContent(content);
			}
			if(Utils.isParmNull(lasttime)){
				work.setLasttime(lasttime);
			}
			if(Utils.isParmNull(kechengid)){
				work.setKechengid(kechengid);
			}
			if(teaService.updateHomeWork(work)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("���³ɹ�");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("����ʧ��");
				return ret;
			}
		}else{
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
	}
	/**
	 * ɾ����ҵ
	 * @param teacherid ��ʦ���
	 * @param token token
	 * @param workid ��ҵ���
	 * @return
	 */
	@RequestMapping(value="/deleteHomeWork",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteHomeWork(String teacherid,String token,String workid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||Utils.isParmNull(workid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(teacherid, token)){
			if(teaService.deleteHomeWork(workid)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("ɾ���ɹ�");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("ɾ��ʧ��");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
	}
	/**
	 * ��ȡ����ѧ��
	 * @param teacherid ��ʦID
	 * @param token �û�token
	 * @param pageSize ҳ���С
	 * @param pageNum ҳ��
	 * @param type ���� 1.��ʾPC  2.�ƶ���
	 * @return
	 */
	@RequestMapping(value="/getAllStudent",method=RequestMethod.POST)
	@ResponseBody
	public Object getAllStudent(String teacherid,String token,Integer pageSize,Integer pageNum,Integer type,String classid,String kechengid){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||type==null){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
		List<StudentInfo> list = teaService.getAllStudent(pageSize, pageNum, classid, kechengid, teacherid);
		ret.setCode(ret.SUCCESS);
		ret.setMessage("��ѯ�ɹ�");
		Map m=new HashMap();
		m.put("pageSize", pageSize);
		m.put("pageNum", pageNum);
		m.put("list", list);
		return ret;
	}
}
