package com.lixian.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lixian.model.HomeWorkInfo;
import com.lixian.model.Student;
import com.lixian.model.StudentInfo;
import com.lixian.model.Stupro;
import com.lixian.model.StuproInfo;
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
			return ret;
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
	public Object deleteKecheng(String stuid,String token,String kechengid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)||Utils.isParmNull(kechengid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(stuid, token)){
			if(stuService.deleteKecheng(kechengid,stuid)){
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
	/**
	 * ��ѯѧ����ѡ�γ�
	 * @param stuid
	 * @return
	 */
	@RequestMapping(value="/showStudentKc",method=RequestMethod.POST)
	@ResponseBody
	public Object showkexuanKecheng(String stuid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setData(stuService.getStudentCanKecheng(stuid));
		ret.setMessage("��ѯ�ɹ�");
		return ret;
	}
	/**
	 * ��ѯѧ����ѡ�γ�
	 * @param stuid
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/getYixuanKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object showYixuanKecheng(String stuid,String token){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("��ѯ�ɹ�");
		ret.setData(stuService.getStudentHaveKecheng(stuid));
		return ret;
	}
	
	/**
	 * �ύ��ҵ
	 * @param stuid ѧ��
	 * @param token token
	 * @param homeworkid
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/commitHomeWork",method=RequestMethod.POST)
	@ResponseBody
	public Object commitHomeWork(String stuid,String token,String homeworkid,@RequestParam("file")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)||Utils.isParmNull(homeworkid)||file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(checkUser(stuid, token)){
			String path = saveFile(file,stuid,homeworkid);
			if(path==null){
				ret.setCode(ret.FILESAVEERROR);
				ret.setMessage("�ļ��洢�쳣");
			}
			System.out.println(path);
			if(stuService.commitHomeWork(stuid, path, homeworkid)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("�ϴ��ɹ�");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("��¼�洢ʧ��");
				return ret;
			}
			
		}else{
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
	}

	private String saveFile(MultipartFile file,String stuid,String homeworkid) {
		// TODO Auto-generated method stub
		String root = System.getProperty("rootpath");
		
		String uuid = UUID.randomUUID().toString();
		String string = uuid.substring(0, 3);
		
			File f = new File(root+File.separator+"homework"+File.separator+stuid+File.separator+homeworkid+File.separator+string+file.getOriginalFilename());
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			try {
				file.transferTo(f);
				return File.separator+"homework"+File.separator+stuid+File.separator+homeworkid+File.separator+string+file.getOriginalFilename();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				return null;
			}
	
	}
	/**
	 * �鿴��ҵ
	 * @param stuid
	 * @return
	 */
	@RequestMapping(value="/showHomeWork",method=RequestMethod.POST)
	@ResponseBody
	public Object showHomeWork(String stuid,String token){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)){
			ret.setMessage("����Ϊ��");
			ret.setCode(ret.PARMISNULL);
			return ret;
		}
		if(checkUser(stuid, token)){
			List<HomeWorkInfo> list = stuService.showHomeWork(stuid);
			ret.setMessage("��ѯ�ɹ�");
			ret.setCode(ret.SUCCESS);
			ret.setData(list);
			return ret;
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
		
	}
	@RequestMapping(value="/testFile")
	public ModelAndView testFile(){
		System.out.println("ִ�����ض���");
		return new ModelAndView("forward:/import_class.html");
	}
	/**
	 * ѧ���ύ��ҵ��Ϣ��ѯ
	 * @param stuid
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/showScore",method=RequestMethod.POST)
	@ResponseBody
	public Object showScore(String stuid,String token){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����Ϊ��");
			return ret;
		}
		if(!checkUser(stuid, token)){
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("�û����Ϸ�");
			return ret;
		}
		List<StuproInfo> list = stuService.showStupro(stuid);
		ret.setCode(ret.SUCCESS);
		ret.setMessage("��ѯ�ɹ�");
		ret.setData(list);
		return ret;
	}
	
}
