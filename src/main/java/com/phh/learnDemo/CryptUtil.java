package com.phh.learnDemo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.mozilla.universalchardet.UniversalDetector;

public class CryptUtil {

	private  static String key = "!@#$%^&*";
	
	/**
	 * ����Ϣ���н���
	 * ���������޷����ܣ���ֱ�ӷ��ؿգ�����ΪһЩ����ݵ��½ӿڵ���ʧ�ܣ�
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String message)
			    throws Exception
	{
		
		try{
			
			 byte[] aryByte = new byte[message.length() / 2];
			    
			    for (int x = 0; x < message.length() / 2; x++)
			    {
			    	String tmp = message.substring(x * 2, x * 2 + 2);
			    	
			        int i = (Integer.parseInt(tmp, 16));
			        aryByte[x] = (byte)i;
			    }
			    
			    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			    DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			    IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			    
			    cipher.init(2, secretKey, iv);
			    
			    byte[] retByte = cipher.doFinal(aryByte);
			    byte[] detect = retByte ;
			    while(retByte != null && detect.length < 1000){
//			    	System.arraycopy(retByte,retByte.length-1 ,detect ,0,retByte.length-1 );
			    	detect = append(retByte, detect);
			    }
			    String encoding = detect(detect);
			    System.out.println(encoding);
			    return new String(retByte, encoding);
		}catch(Exception ex){
			return "";
		}
	   
	}
	
	
	
	public static String decryptEx(String message, String keyCode)
		    throws Exception
{
	
	try{
		
		 byte[] aryByte = new byte[message.length() / 2];
		    
		    for (int x = 0; x < message.length() / 2; x++)
		    {
		    	String tmp = message.substring(x * 2, x * 2 + 2);
		    	
		        int i = (Integer.parseInt(tmp, 16));
		        aryByte[x] = (byte)i;
		    }
		    
		    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		    DESKeySpec desKeySpec = new DESKeySpec(keyCode.getBytes());
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		    IvParameterSpec iv = new IvParameterSpec(keyCode.getBytes());
		    
		    cipher.init(2, secretKey, iv);
		    
		    byte[] retByte = cipher.doFinal(aryByte);
		    return new String(retByte, "gbk");
	}catch(Exception ex){
		return "";
	}
   
}
	
	
	/**
	 * ����Ϣ���м���
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message)
			    throws Exception
	{
		 Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		 DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		 SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		 IvParameterSpec iv = new IvParameterSpec(key.getBytes());
		 cipher.init(1, secretKey, iv);
		 byte[] b = cipher.doFinal(message.getBytes("GB18030"));
		 return StringUtil.byte2HexStr(b);
	}

	public static String decryptEx2(String message, String keyCode, String siv)
		    throws Exception
	{
	
	try{
		
		 byte[] aryByte = new byte[message.length() / 2];
		    
		    for (int x = 0; x < message.length() / 2; x++)
		    {
		    	String tmp = message.substring(x * 2, x * 2 + 2);
		    	
		        int i = (Integer.parseInt(tmp, 16));
		        aryByte[x] = (byte)i;
		    }
		    
		    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		    DESKeySpec desKeySpec = new DESKeySpec(keyCode.getBytes());
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		    IvParameterSpec iv = new IvParameterSpec(siv.getBytes());
		    
		    cipher.init(2, secretKey, iv);
		    
		    byte[] retByte = cipher.doFinal(aryByte);
		    return new String(retByte, "gbk");
	}catch(Exception ex){
		return "";
	}
	
	}
	
	public static byte[] append(byte[] org, byte[] to) {

		byte[] newByte = new byte[org.length + to.length];

		System.arraycopy(org, 0, newByte, 0, org.length);

		System.arraycopy(to, 0, newByte, org.length, to.length);

		return newByte;

		}
	
	public static boolean isMessyCode(String str) {
		  for (int i = 0; i < str.length(); i++) {
		   char c = str.charAt(i);
		   // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
		   //从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
		   //System.out.println("--- " + (int) c);
		   if ((int) c == 0xfffd) {
		    // 存在乱码
		    //System.out.println("存在乱码 " + (int) c);
		    return true;
		   }
		  }
		  return false; 
		 }
	
	public static String guessEncoding(byte[] bytes) {  
	    String DEFAULT_ENCODING = "UTF-8";  
	    org.mozilla.universalchardet.UniversalDetector detector =  
	        new org.mozilla.universalchardet.UniversalDetector(null);  
	    detector.handleData(bytes, 0, bytes.length);  
	    detector.dataEnd();  
	    String encoding = detector.getDetectedCharset();  
	    detector.reset();  
	    if (encoding == null) {  
	        encoding = DEFAULT_ENCODING;  
	    }  
	    return encoding;  
	}  
	
	public static String detect(byte[] content) {
        UniversalDetector detector = new UniversalDetector(null);
            //开始给一部分数据，让学习一下啊，官方建议是1000个byte左右（当然这1000个byte你得包含中文之类的）
        detector.handleData(content, 0, content.length);
            //识别结束必须调用这个方法
        detector.dataEnd();
        //神奇的时刻就在这个方法了，返回字符集编码。
        return detector.getDetectedCharset();
    }
	
	public static void main(String[] args) throws Exception {
		String a = "龙海飞";
//		boolean aa = java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(a); //判断是不是GBK编码 即是否乱码
//		System.out.println(aa);
		String encrypt = encrypt(a);
		System.out.println(encrypt);
		String decrypt = decrypt(encrypt);
		System.out.println(decrypt);
//		System.out.println(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode("??升降椅爆炸女子下体被炸穿 肚内取出十多块碎片！！"));
//		System.out.println(decrypt("CE8A8685160179E61F2575E848BAD803434902C29561AEE5"));new 
//		String aa = "중국어사전";
//		byte [] bb = aa.getBytes("UTF-8");
//		System.out.println(detect(bb));
//		System.out.println(decrypt("75AC6478977772A1"));
		
//		byte [] source = "phhasd".getBytes();
//		byte [] dest  = "test".getBytes();
//		byte [] result = append(source, dest);
		//		System.out.println(new String(result));
//		byte [] aa = "phhasd".getBytes();
//		byte[] newByte = "zxcvbnmbnmbnm".getBytes();
//
//		System.arraycopy(aa, 0, newByte, 0, 3);
//		
//		System.out.println(Arrays.asList(newByte).toString());
		
		
	}
}
