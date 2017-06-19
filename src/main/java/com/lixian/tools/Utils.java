package com.lixian.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;

public class Utils {
	
	public static boolean isParmNull(String obj){
		if(obj==null||obj.equals("")){
			return true;
		}
		return false;
	}
	//26876905
	//99799883
	//59648659
	//57740557
	public static void main(String[] args) {
		String imei="355099048156593";
		String jiami = jiami(imei,System.currentTimeMillis()/1000);
		System.out.println(jiami);
	}
	public static String jiami(String imei,long time){
		long l = System.currentTimeMillis();
		String md5 = MD5Tools.MD5(imei+l);
		char[] array = md5.toCharArray();
		int[] it=new int[8];
		for (int i = 0; i < it.length; i++) {
			it[i]=(int)array[i]%10;
		}
		String password="";
		for (int i = 0; i < it.length; i++) {
			password=password+it[i];
		}
		return password;
	}
}
