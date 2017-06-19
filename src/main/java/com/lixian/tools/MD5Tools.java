package com.lixian.tools;
import java.security.MessageDigest;  
  
/** 
 * MD5���ܹ����� 
 */  
public abstract class MD5Tools  {  
    public final static String MD5(String pwd) {  
        //���ڼ��ܵ��ַ�  
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            //ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte���У���������洢��һ���µ� byte������  
            byte[] btInput = pwd.getBytes();  
               
            //��ϢժҪ�ǰ�ȫ�ĵ����ϣ�����������������С�����ݣ�������̶����ȵĹ�ϣֵ��  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
               
            //MessageDigest����ͨ��ʹ�� update�����������ݣ� ʹ��ָ����byte�������ժҪ  
            mdInst.update(btInput);  
               
            // ժҪ����֮��ͨ������digest����ִ�й�ϣ���㣬�������  
            byte[] md = mdInst.digest();  
               
            // ������ת����ʮ�����Ƶ��ַ�����ʽ  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {   //  i = 0  
                byte byte0 = md[i];  //95  
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
                str[k++] = md5String[byte0 & 0xf];   //   F  
            }  
               
            //���ؾ������ܺ���ַ���  
            return new String(str);  
               
        } catch (Exception e) {  
            return null;  
        }  
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