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
	 * 添加教师
	 * @param teachername 教师名字
	 * @param password 密码
	 * @param telphone 电话，可以为空
	 * @param email 邮件地址，可以为空
	 * @return
	 */
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	@ResponseBody
	public Object addTeacher(String teacherid,String teachername,String password,String telphone,String email){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teachername)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
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
			ret.setMessage("添加成功");
			ret.setData(tea);
			return ret;
		}else {
			ret.setCode(ret.DBERROR);
			ret.setMessage("数据库异常");
			return ret;
		}
		
	}
	
	/**
	 * 更新教师用户信息
	 * @param teacherid 教师编号
	 * @param email 电子邮件
	 * @param tel 电话
	 * @param token token
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object updateTeacherInfo(String teacherid,String email,String tel,String token){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("教师id为空");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("用户不合法");
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
			ret.setMessage("更新成功");
			return ret;
		}else{
			ret.setCode(ret.DBERROR);
			ret.setMessage("为找到该教师");
			return ret;
		}
		
	}
	/**
	 * 添加课程
	 * @param name 课程名
	 * @param teacherid 教师编号
	 * @param classid 班级编号
	 * @param stunum 学生人数
	 * @param token token
	 * @return
	 */
	@RequestMapping(value="/addKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object addKeChen(String name,String teacherid,String classid,Integer stunum,String token){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(name)||Utils.isParmNull(teacherid)||Utils.isParmNull(classid)||stunum==null||stunum<0){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
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
			ret.setMessage("添加成功");
			return ret;
		}
		ret.setCode(ret.DBERROR);
		ret.setMessage("数据库异常");
		return ret;
	}
	/**
	 * 教师登录
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
			ret.setMessage("参数为空");
			return ret;
		}
		Teacher tea = teaService.getTeacher(teacherid);
		if(tea==null){
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("未找到该用户");
			return ret;
		}
		if(tea.getPassword().equals(MD5Tools.MD5(password))){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("登录成功");
			Map m=new HashMap();
			m.put("teacher", tea);
			m.put("token", MD5Tools.MD5(tea.getId()+tea.getPassword()));
			ret.setData(m);
			return ret;
		}else{
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("密码错误");
			return ret;	
		}
		
	}
	/**
	 * 校验教师是否合法
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
	 * 发布作业
	 * @param teacherid 教师编号
	 * @param token token
	 * @param workname 作业名
	 * @param content 内容
	 * @param lasttime 最后提交时间 时间戳
	 * @param kechengid 课程编号
	 * @return
	 */
	@RequestMapping(value="/addHomework",method=RequestMethod.POST)
	@ResponseBody
	public Object addHomeWrok(String teacherid,String token,String workname,String content,String lasttime,String kechengid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||Utils.isParmNull(workname)||Utils.isParmNull(content)||Utils.isParmNull(lasttime)||Utils.isParmNull(kechengid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("用户不合法");
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
			ret.setMessage("发布成功");
			return ret;
		}
		ret.setCode(ret.DBERROR);
		ret.setMessage("发布失败");
		return ret;
	}
	/**
	 * 更新作业信息
	 * @param teacherid 教师ID
	 * @param token token
	 * @param workname 作业名字
	 * @param content 作业内容
	 * @param lasttime 最迟提交时间
	 * @param kechengid 课程id
	 * @param workid 作业id
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
				ret.setMessage("未找到该作业");
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
				ret.setMessage("更新成功");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("更新失败");
				return ret;
			}
		}else{
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("用户不合法");
			return ret;
		}
	}
	/**
	 * 删除作业
	 * @param teacherid 教师编号
	 * @param token token
	 * @param workid 作业编号
	 * @return
	 */
	@RequestMapping(value="/deleteHomeWork",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteHomeWork(String teacherid,String token,String workid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||Utils.isParmNull(workid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		if(checkUser(teacherid, token)){
			if(teaService.deleteHomeWork(workid)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("删除成功");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("删除失败");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("用户不合法");
			return ret;
		}
	}
	/**
	 * 获取所有学生
	 * @param teacherid 教师ID
	 * @param token 用户token
	 * @param pageSize 页面大小
	 * @param pageNum 页数
	 * @param type 类型 1.表示PC  2.移动端
	 * @return
	 */
	@RequestMapping(value="/getAllStudent",method=RequestMethod.POST)
	@ResponseBody
	public Object getAllStudent(String teacherid,String token,Integer pageSize,Integer pageNum,Integer type,String classid,String kechengid){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(teacherid)||Utils.isParmNull(token)||type==null){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		if(!checkUser(teacherid, token)){
			ret.setCode(ret.PASSWORDERROR);
			ret.setMessage("用户不合法");
			return ret;
		}
		List<StudentInfo> list = teaService.getAllStudent(pageSize, pageNum, classid, kechengid, teacherid);
		ret.setCode(ret.SUCCESS);
		ret.setMessage("查询成功");
		Map m=new HashMap();
		m.put("pageSize", pageSize);
		m.put("pageNum", pageNum);
		m.put("list", list);
		return ret;
	}
}
