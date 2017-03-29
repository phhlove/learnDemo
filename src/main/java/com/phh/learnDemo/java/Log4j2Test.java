package com.phh.learnDemo.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class Log4j2Test {
    private  static Logger log = LoggerFactory.getLogger(Log4j2Test.class);
    
    private void test(){ 
    	log.info("你好啊");  
        log.debug("我是debug");  
        log.error("错了");  
        log.trace("这是什么"); 
        log.info("zheshi asida {}", 123);
    }
    
    public static void main(String[] args) throws InterruptedException {
    	Log4j2Test log4j2Test= new Log4j2Test();
    	log4j2Test.test();
    	System.out.println("phh");
    	System.out.println("phh");
    	System.out.println("phh");
    	System.out.println("phh");
    	System.out.println("phh");
    	System.out.println("phh");
    	Thread.sleep(1000*7*60);
    	log4j2Test.test();
	}
}
