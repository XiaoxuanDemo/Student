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
	 * 用户登录
	 * @param stuid 学生id
	 * @param password 密码
	 * @return token  每次请求带上token token=id+密码 的MD5
	 */
	//{"message":"登录成功","code":200,"data":{"student":{"stuname":"刘壮实4","id":"A2013121004","classname":"幼儿园大班3","password":"E10ADC3949BA59ABBE56E057F20F883E"},"token":"B650F8AD7D82404E65E4E5A0440B943C"}}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object Login(String stuid,String password){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(password)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		Student stu = new Student();
		stu.setPassword(MD5Tools.MD5(password));
		stu.setId(stuid);
		StudentInfo login = stuService.login(stu);
		if(login==null){
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("用户名或者密码不正确");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("登录成功");
		Map m=new HashMap();
		m.put("student", login);
		m.put("token", MD5Tools.MD5(login.getId()+login.getPassword()));
		ret.setData(m);
		return ret;
	}
	/**
	 * 获取用户展示信息
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
			ret.setMessage("参数为空");
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
			ret.setMessage("查询成功");
			return ret;
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("用户非法");
			return ret;
		}
	}
	/**
	 * 校验用户是否合法
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
	 * 学生选课
	 * @param stuid 学号
	 * @param token token
	 * @param kechengid 课程号
	 * @return
	 */
	@RequestMapping(value="/checkKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object choseKecheng(String stuid,String token,String kechengid){
		MessageReturn ret = new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(token)||Utils.isParmNull(kechengid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		if(checkUser(stuid, token)){
			if(stuService.choseKecheng(stuid, kechengid)){
				ret.setMessage("选课成功");
				ret.setCode(ret.SUCCESS);
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("数据库异常");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("用户不合法");
			return ret;
		}
	}
	/**
	 * 删除选课
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
			ret.setMessage("参数为空");
			return ret;
		}
		if(checkUser(stuid, token)){
			if(stuService.deleteKecheng(id)){
				ret.setCode(ret.SUCCESS);
				ret.setMessage("删除成功");
				return ret;
			}else{
				ret.setCode(ret.DBERROR);
				ret.setMessage("数据库异常");
				return ret;
			}
		}else{
			ret.setCode(ret.RECODEISNOFOUND);
			ret.setMessage("用户不合法");
			return ret;
		}
	}
	/**
	 * 查询学生可选课程
	 * @param stuid
	 * @return
	 */
	@RequestMapping(value="/showStudentKc",method=RequestMethod.POST)
	@ResponseBody
	public Object showKecheng(String stuid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setData(stuService.getStudentCanKecheng(stuid));
		ret.setMessage("查询成功");
		return ret;
	}
}
