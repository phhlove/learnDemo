package com.phh.learnDemo;

import org.apache.commons.lang3.StringUtils;


public class StringUtil {

	public static String trim(String source, char c) {
		if(source == null || source.length() == 0) {
			return source;
		}
		source = source.trim();
		int len = source.length();
		int st = 0;
		
		while ((st < len) && (source.charAt(st) == c)) {
			st++;
		}
		while ((st < len) && (source.charAt(len - 1) == c)) {
			len--;
		}
		return ((st > 0) || (len < source.length())) ? source.substring(st, len) : source;
	}
	
	/**
	 * �ж������Ƿ�����ַ�����
	 * @param num  ��Ҫ�жϵ�����
	 * @param numStr �����ַ�
	 * @return 
	 */
	public static boolean in(int num, String numStr){
		return contains(numStr, num, ",");
	}

	/**
	 *  �ж�str�Ƿ����targetArray����
	 * @param str
	 * @param targetArray
	 * @return
	 * @author xuan
	 */
	public static boolean in(String str, String...targetArray) {
		for(String target : targetArray) {
			if(target.equals(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * �ַ�ת����ʮ������ַ�
	 */

	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * ʮ�����ת���ַ�
	 */

	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * bytesת����ʮ������ַ�
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * bytesת����ʮ������ַ�
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		System.out.println(l);
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}

	/**
	 * String���ַ�ת����unicode��String
	 */
	public static String str2Unicode(String strText) throws Exception {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128) {
				strRet += "//u" + strHex;
			} else {
				// ��λ��ǰ�油00
				strRet += "//u00" + strHex;
			}
		}
		return strRet;
	}

	/**
	 * unicode��Stringת����String���ַ�
	 */
	public static String unicode2Str(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// ��λ��Ҫ����00��ת
			String s1 = s.substring(2, 4) + "00";
			// ��λֱ��ת
			String s2 = s.substring(4);
			// ��16���Ƶ�stringתΪint
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// ��intת��Ϊ�ַ�
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}

	/**
	 * ƴ��argsΪ�ַ�
	 * 
	 * @param args
	 * @return
	 * @author xuan
	 */
	public static String append(Object... args) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append(arg);
		}
		return sb.toString();
	}

	/**
	 * ����ת�ַ�
	 * 
	 * @param arge
	 * @return ���ա�������
	 */
	public static String intToString(Integer arge) {
		String str = "";
		if (arge != null && arge.intValue() > 0) {
			str = arge.toString();
		}
		return str;
	}

	/**
	 * ������oracle�е�nvl�������value��Ϊnull�򷵻�value, Ϊnull�򷵻�defaultValue
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 * @author xuan
	 */
	public static String nvl(String value, String defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * ���� sourceStr.contains(pad + targetStr + pad)
	 * @param sourceStr Դ�ַ�
	 * @param targetObj ��Ҫ���ҵ����ַ�
	 * @param pad targetStr���ߵ�����ַ�
	 * @return
	 * @author xuan
	 */
	public static boolean contains(String sourceStr, Object targetObj, String pad) {
		return padIfNeed(sourceStr, pad).contains(padIfNeed(targetObj.toString(), pad));
	}
	
	/**
	 * ���� sourceStr.contains(pad + targetStr + pad)
	 * @param sourceStr Դ�ַ�
	 * @param targetStr ��Ҫ���ҵ����ַ�
	 * @param pad targetStr���ߵ�����ַ�
	 * @return
	 * @author xuan
	 */
	public static <T extends Number> boolean contains(String sourceStr, T targetStr, String pad) {
		return padIfNeed(sourceStr, pad).contains(append(pad, targetStr, pad));
	}
	
	/**
	 * ���str������pad��ʼ�����ڿ�ʼ������pad���������pad�������ڽ�β����pad
	 * @param str
	 * @param pad
	 * @return
	 * @author xuan
	 */
	private static String padIfNeed(String str, String pad) {
		StringBuilder sb = new StringBuilder(str.length() + pad.length() * 2);
		if(!str.startsWith(pad)) {
			sb.append(pad);
		}
		sb.append(str);
		if(!str.endsWith(pad)) {
			sb.append(pad);
		}
		return sb.toString();
	}
	
	/**
	 * ���ַ������ĸ�ĳɴ�д
	 * 
	 * @param str
	 * @return
	 * @author xuan
	 */
	public static String firstCharUp(String str) {
		if(StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		char firstChar = str.charAt(0);
		if(firstChar >= 'a' && firstChar <= 'z') {
			firstChar = (char)(firstChar - ('a' - 'A'));
		}
		sb.append(firstChar);
		for(int i = 1, length = str.length(); i < length; i++) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	/**
	 * ���ַ������ĸ�ĳ�Сд
	 * 
	 * @param str
	 * @return
	 * @author xuan
	 */
	public static String firstCharDown(String str) {
		if(StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		char firstChar = str.charAt(0);
		if(firstChar >= 'A' && firstChar <= 'Z') {
			firstChar = (char)(firstChar + ('a' - 'A'));
		}
		sb.append(firstChar);
		for(int i = 1, length = str.length(); i < length; i++) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * ��builder�ڵ�from�ַ��滻Ϊto�ַ�
	 * @param builder
	 * @param from
	 * @param to
	 * @author xuan
	 */
	public static void replaceAll(StringBuilder builder, String from, String to) {
		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length(); // index�Ƶ��滻���ַ�֮��
			index = builder.indexOf(from, index);
		}
	}
	

}
