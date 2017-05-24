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

import com.lixian.model.Teacher;
import com.lixian.service.TeacherService;
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
	/**
	 * 导入教师，文件为.xlsx
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importuser",method=RequestMethod.POST)
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
						UUID uuid = UUID.randomUUID();
						tea.setId(uuid.toString());
						String teaname = row.getCell(0).toString();
						String pwd=row.getCell(1).toString();
						if(Utils.isParmNull(teaname)||Utils.isParmNull(pwd)){
							throw new NullPointerException();
						}
						tea.setTeachername(teaname);
						tea.setPassword(pwd);
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
	@RequestMapping(value="/addClass",method=RequestMethod.POST)
	@ResponseBody
	public Object addClass(String classname){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(classname)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("班级名为空");
			return ret;
		}
		
		return ret;
	}
}
