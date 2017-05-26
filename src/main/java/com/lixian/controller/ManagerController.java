package com.lixian.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lixian.model.Banji;
import com.lixian.model.Kecheng;
import com.lixian.model.Student;
import com.lixian.model.Teacher;
import com.lixian.service.ManagerService;
import com.lixian.service.StudentService;
import com.lixian.service.TeacherService;
import com.lixian.tools.MD5Tools;
import com.lixian.tools.MessageReturn;
import com.lixian.tools.Utils;
/**
 * 管理接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Resource
	private TeacherService teaService;
	@Resource
	private ManagerService manService;
	@Resource
	private StudentService stuService;
	/**
	 * 导入教师，文件为.xlsx
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importTeacher",method=RequestMethod.POST)
	@ResponseBody
	public Object upLoadTeacher(@RequestParam("teachers")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("文件为空");
			return ret;
		}
		List<Teacher> teas=new ArrayList<Teacher>();
		
		try {
			XSSFWorkbook book=new XSSFWorkbook(file.getInputStream());
			for (Sheet sheet : book) {
				if(sheet!=null){
					for (int i = 0; i < sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						Teacher tea = new Teacher();
						String id = row.getCell(0).toString();
						
						String teaname = row.getCell(1).toString();
						String pwd=row.getCell(2).toString();
						if(Utils.isParmNull(teaname)||Utils.isParmNull(pwd)||Utils.isParmNull(id)){
							throw new NullPointerException();
						}
						tea.setId(id);
						tea.setTeachername(teaname);
						tea.setPassword(MD5Tools.MD5(pwd));
						teas.add(tea);
					}
				}
			}
			teaService.importTeacher(teas);
			ret.setCode(ret.SUCCESS);
			ret.setMessage("导入成功");
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setCode(ret.IOERROR);
			ret.setMessage("IO异常");
			return ret;
		}catch (NullPointerException e) {
			// TODO: handle exception
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("表格中存在空元素，无法完成导入操作");
			return ret;
		}catch(RuntimeException e){
			ret.setCode(ret.DBERROR);
			ret.setMessage("数据库异常");
			return ret;
		}
		
	}
	/**
	 * 添加班级
	 * @param classname
	 * @return
	 */
	@RequestMapping(value="/addClass",method=RequestMethod.POST)
	@ResponseBody
	public Object addClass(String classname){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(classname)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("班级名为空");
			return ret;
		}
		Banji bj=new Banji();
		bj.setClassid(UUID.randomUUID().toString());
		bj.setClassname(classname);
		manService.addClass(bj);
		return ret;
	}
	/**
	 * 导入班级
	 * @param file班级文件
	 * @return
	 */
	@RequestMapping(value="/importClass",method=RequestMethod.POST)
	@ResponseBody
	public Object importClass(@RequestParam("file")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
		}
		try {
			XSSFWorkbook b=new XSSFWorkbook(file.getInputStream());
			List<Banji> bjs=new ArrayList<Banji>();
			for (Sheet sheet : b) {
				if(sheet!=null){
					for (int i = 0; i < sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						String classname = row.getCell(0).toString();
						if(Utils.isParmNull(classname)){
							throw new NullPointerException();
						}
						UUID uuid = UUID.randomUUID();
						Banji banji = new Banji();
						banji.setClassid(uuid.toString());
						banji.setClassname(classname);
						bjs.add(banji);
					}
				}
			}
			manService.importClass(bjs);
			ret.setMessage("导入成功");
			ret.setCode(ret.SUCCESS);
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setCode(ret.IOERROR);
			ret.setMessage("服务器IO异常");
			return ret;
		}catch(NullPointerException e){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("请检查数据表格式是否正确");
			return ret;
		}
		catch (Exception e) {
			// TODO: handle exception
			ret.setCode(ret.DBERROR);
			ret.setMessage("批量导入失败请检查后重试");
			return ret;
		}
		
	}
	/**
	 * 添加学生
	 * @param stuid 学号
	 * @param stuname 姓名
	 * @param password 密码
	 * @param classid 班级ID
	 * @return
	 */
	@RequestMapping(value="/addStudent",method=RequestMethod.POST)
	@ResponseBody
	public Object addStudent(String stuid,String stuname,String password,String classid){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(stuid)||Utils.isParmNull(classid)||Utils.isParmNull(classid)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		Student stu = new Student();
		stu.setId(stuid);
		stu.setClassid(classid);
		stu.setPassword(MD5Tools.MD5(password));
		stu.setClassid(classid);
		if(stuService.addUser(stu)){
			ret.setCode(ret.SUCCESS);
			ret.setMessage("添加成功");
			return ret;
		}
		ret.setCode(ret.DBERROR);
		ret.setMessage("数据库异常");
		return ret;
	}
	/**
	 * 导入学生
	 * @param classid
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importStudent",method=RequestMethod.POST)
	@ResponseBody
	public Object importStudent(String classid,@RequestParam("file")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(classid)||file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("参数为空");
			return ret;
		}
		List<Student> stus=new ArrayList<Student>();
		try {
			XSSFWorkbook book = new XSSFWorkbook(file.getInputStream());
			for (Sheet sheet : book) {
				if(sheet!=null){
					for (int i = 0; i < sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						String stuid = row.getCell(0).toString();
						String stuname = row.getCell(1).toString();
						if(Utils.isParmNull(stuid)||Utils.isParmNull(stuname)){
							throw new NullPointerException();
						}
						Student stu = new Student();
						stu.setClassid(classid);
						stu.setStunam(stuname);
						stu.setPassword(MD5Tools.MD5("123456"));
						stu.setId(stuid);
						stus.add(stu);
					}
				}
			}
			stuService.importUser(stus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setCode(ret.IOERROR);
			ret.setMessage("文件异常");
			return ret;
		}catch(NullPointerException e){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("表格数据格式有误");
			return ret;
		}catch(RuntimeException e){
			ret.setCode(ret.DBERROR);
			ret.setMessage("数据库存储异常");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("导入成功，一共导入了"+stus.size()+"名学生");
		return ret;
	}
	/**
	 * 批量导入课程
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importKecheng",method=RequestMethod.POST)
	@ResponseBody
	public Object importKecheng(@RequestParam("file")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("文件为空");
			return ret;
		}
		try {
			List<Kecheng> kcs=new ArrayList<Kecheng>();
			XSSFWorkbook book = new XSSFWorkbook(file.getInputStream());
			for (Sheet sheet : book) {
				if(sheet!=null){
					for (int i = 0; i < sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						String name = row.getCell(0).toString();
						String teacherid=row.getCell(1).toString();
						String classid = row.getCell(2).toString();
						float stunum=Float.valueOf(row.getCell(3).toString());
						int stunumi=(int) stunum;
						if(Utils.isParmNull(classid)||Utils.isParmNull(name)||Utils.isParmNull(teacherid)){
							throw new NullPointerException();
						}
						Kecheng kc = new Kecheng();
						kc.setId(UUID.randomUUID().toString());
						kc.setName(name);
						kc.setStunum(stunumi);
						kc.setTeahcerid(teacherid);
						kc.setClassid(classid);
						kcs.add(kc);
					}
				}
			}
			teaService.improtKecheng(kcs);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setCode(ret.IOERROR);
			ret.setMessage("文件IO异常");
			return ret;
		}catch(NullPointerException e){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("表格数据异常");
			return ret;
		}catch(NumberFormatException e){
			ret.setCode(ret.PARMERROR);
			ret.setMessage("表格数据异常");
			return ret;
		}
		ret.setCode(ret.SUCCESS);
		ret.setMessage("导入成功");
		return ret;
	}
}
