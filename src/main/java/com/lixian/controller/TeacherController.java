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
	 * 添加教师
	 * @param teachername 教师名字
	 * @param password 密码
	 * @param telphone 电话，可以为空
	 * @param email 邮件地址，可以为空
	 * @return
	 */
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	@ResponseBody
	public Object addTeacher(String teachername,String password,String telphone,String email){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teachername)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
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
			ret.setMessage("添加成功");
			ret.setData(tea);
			return ret;
		}else {
			ret.setCode(ret.DBERROR);
			ret.setMessage("数据库异常");
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
