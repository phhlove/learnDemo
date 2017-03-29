package com.phh.learnDemo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 
 *
 * @Description: Guava Cache是一个全内存的本地缓存实现，它提供了线程安全的实现机制。
 *                  整体上来说Guava cache 是本地缓存的不二之选，简单易用，性能好。
 * @author phh
 * @date 2016年8月25日
 *
 */
public class GuavaCacheDemo {
	public static void TestLoadingCache() throws Exception{
		LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().build(new CacheLoader<String, String>(){
			@Override
			public String load(String key) throws Exception {
				 System.out.println("asd");
				 String strProValue="hello "+key+"!";           
				return strProValue;
			}

		});

		System.out.println("jerry value:" + cacheBuilder.getUnchecked("jerry"));
		System.out.println("jerry value:" + cacheBuilder.get("jerry"));
//		System.out.println("peida value:" + cacheBuilder.get("peida"));
//		System.out.println("peida value:" + cacheBuilder.apply("peida"));
//		System.out.println("lisa value:" + cacheBuilder.apply("lisa"));
//		cacheBuilder.put("harry", "ssdded");
//		System.out.println("harry value:" + cacheBuilder.getUnchecked("harry"));
	}
	
	public static void main(String[] args) throws Exception {
		TestLoadingCache();
	}
}
