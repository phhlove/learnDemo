package com.phh.learnDemo.guava;
/**
 * 
 *
 * @Description:Preconditions优雅的检验参数
 * @author phh
 * @date 2016年8月22日
 *
 */
public class Preconditions {
   public static void main(String[] args) {
	   String name = "asd";
	   Integer age = 1;
//	   String a = com.google.common.base.Preconditions.checkNotNull(name);
	   com.google.common.base.Preconditions.checkArgument(name.length()>0, "neme为\'\'");
	   com.google.common.base.Preconditions.checkArgument(age>0, "age 必须大于0");
}
}
