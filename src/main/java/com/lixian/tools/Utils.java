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

}
