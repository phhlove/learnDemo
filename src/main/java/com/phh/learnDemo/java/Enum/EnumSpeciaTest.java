package com.phh.learnDemo.java.Enum;

import java.text.DateFormat;
import java.util.Date;

public class EnumSpeciaTest {
	
//	public class PhhTest{
//		 enum Gener{YES,NO1,NO2}
////		 PhhTest.Gener gener;
//	 }
     public enum DemoEnum{
    	 DATE_TIME{
    		 @Override
    		 String getInfo(){
    			 return DateFormat.getDateInstance().format(new Date());
    		 }
    	 },CLASSPATH{
    		 @Override
    		 String getInfo(){
    			 return System.getenv("CLASSPATH");
    		 }
    	 },VERSION{

			@Override
			String getInfo() {
				
				return System.getProperty("java.version");
			}
    		 
    	 };
    	 abstract String getInfo();
     }
     
     public static class Enumtest{
    	 static int n;
    	 void f1(int a ){}
     }
     
     public static void main(String[] args) {
		for (DemoEnum demoEnum : DemoEnum.values()) {
			System.out.println(demoEnum.getInfo());
		}
	}
}
