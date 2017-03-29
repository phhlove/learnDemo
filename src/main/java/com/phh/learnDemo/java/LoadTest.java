package com.phh.learnDemo.java;

public class LoadTest {
	  
    public static int k = 0;  
    public static LoadTest t1 = new LoadTest("t1");  
    public static LoadTest t2 = new LoadTest("t2");  
    public static int i = print("i");  
    public static int n = 99;  
       
    public int j = print("j");  
       
    {  
        print("构造块");  
    }  
       
    static {  
        print("静态块");  
    }  
   
    public LoadTest(String str) {  
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);  
        ++i;  
        ++n;  
    }  
   
    private static int print(String str) {  
        System.out.println((++k) + ":" + str + "   i=" + i + "   n=" + n);  
        ++n;  
        return ++i;  
    }  
   
    public static void main(String[] args) {  
      LoadTest t = new LoadTest("init");  
          
//        LoadTest t2 = new LoadTest("init2");  
          
    }  
}
