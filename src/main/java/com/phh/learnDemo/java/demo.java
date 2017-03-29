package com.phh.learnDemo.java;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

/**
 * 
 *描述：
 *@author: phh
 *@date： 日期：2017年2月15日
 *
 *
 */
public class demo implements demo1,demo2 {
	
    int a ;
	@Override
	public void test() {
		System.out.println("aa");
		
	}
	
	/**
	 * 
	 * 
	 *@author: phh
	 *@param a
	 *@param b
	 *@return
	 *@date: 2017年2月15日
	 *
	 */
	public  int fuch(int a ,String b){
		return 1;
	}

	  /**
	   * 
	   * @param args
	   */
	  public static void main(String[] args) {
//		demo1 a = new demo();
//		demo2 b = new demo();
//		Integer a1 = new Integer(128) ;
//		Integer b1 =  new Integer(128);
//		Byte a1 = 1;
//		int b1 = 1;
//		System.out.println(a1==b1);
//		System.out.println(Objects.equals(a1, b1));
		List<String> a = Lists.newArrayList("1","2");
//		for (String string : a) {
//			if("2".equals(string)){
//				a.remove(string);
//			}
//		}
		Iterator<String> it = a.iterator();
		while(it.hasNext()){
			String string = it.next();
			if("2".equals(string)){
				it.remove();
			}
		}
		System.out.println(a.toString());
		
	}
}
