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
 * ����ӿ�
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Resource
	private TeacherService teaService;
	/**
	 * �����ʦ���ļ�Ϊ.xlsx
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importuser",method=RequestMethod.POST)
	@ResponseBody
	public Object upLoadTeacher(@RequestParam("teachers")MultipartFile file){
		MessageReturn ret=new MessageReturn();
		if(file==null||file.isEmpty()){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("�ļ�Ϊ��");
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
			ret.setMessage("����ɹ�");
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setCode(ret.IOERROR);
			ret.setMessage("IO�쳣");
			return ret;
		}catch (NullPointerException e) {
			// TODO: handle exception
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("����д��ڿ�Ԫ�أ��޷���ɵ������");
			return ret;
		}catch(RuntimeException e){
			ret.setCode(ret.DBERROR);
			ret.setMessage("���ݿ��쳣");
			return ret;
		}
		
	}
	@RequestMapping(value="/addClass",method=RequestMethod.POST)
	@ResponseBody
	public Object addClass(String classname){
		MessageReturn ret=new MessageReturn();
		if(Utils.isParmNull(classname)){
			ret.setCode(ret.PARMISNULL);
			ret.setMessage("�༶��Ϊ��");
			return ret;
		}
		
		return ret;
	}
}
