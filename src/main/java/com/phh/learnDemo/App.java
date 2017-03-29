package com.phh.learnDemo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;

import sun.misc.OSEnvironment;

import com.google.common.collect.Lists;
import com.phh.learnDemo.java.OSExecute;


/**
 * Hello world!
 *
 */
public class App 
{
	public class Pair<X,Y>{
		public X x;
		public Y y;
		public Pair(X x, Y y){
			this.x = x;
			this.y = y;
		}
	}
	
	public class Pair1{
		public String x;
		public Integer y;
//		public <XXX , Integer>Pair1(XXX x, y){
//			this.x =  x;
//			this.y =  y;
//		}
	}
	
	enum testEnum {
		GR ,ADD , SUB ;
		
//		public String toString(){
//			String id = name();
//			return null;
//		}
		
		}
	
    public static void main( String[] args ) throws Exception
    {
//        System.out.println(ShiftEnum.getName(2));

//
    	//        ShiftEnum a =  EnumUtils.getEnum(ShiftEnum.class, "A"+index);
//        System.out.println(a.getName());
//    	System.
//    	ArrayList<String> alist = Lists.newArrayList("12","123123","345345");
//    	
//    	System.out.println(alist.toArray()[0]);
//    	OSExecute.command("javap testEnum");
//    	for (String s : "GR,ADD,SUB".split(",")) {
////			System.out.println(s);
//			testEnum ss = Enum.valueOf(testEnum.class, s);
//			testEnum aa = EnumUtils.getEnum(testEnum.class, s);
//			System.out.println(ss);
//			System.out.println(aa);
//			System.out.println(aa.getDeclaringClass());
//		}
//    	List<Integer> freeItemIDList = new ArrayList<Integer>();
//    	freeItemIDList.add(1);
//    	freeItemIDList.add(4);
//    	Integer [] intArr = freeItemIDList.toArray(new Integer[freeItemIDList.size()]);
//    	freeItemIDList = Arrays.asList(intArr);
//    	System.out.println(intArr);
//    	Map<String, Object> aa = new HashMap<String, Object>();
//    	aa.put("phh", 123);
//    	int qty = 3;
//    	String a = MessageFormat.format("早餐 *{0}" , qty);
//    	
    	String a = "A";
    	char d = a.charAt(0);
    	int b = d;
    	System.out.println(Long.parseLong(String.valueOf((int)a.charAt(0))));
    }
}
