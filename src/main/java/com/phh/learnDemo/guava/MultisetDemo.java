package com.phh.learnDemo.guava;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

/**
 * 
 *
 * @Description: Multiset和Set的区别就是可以保存多个相同的对象。
 *               在JDK中，List和Set有一个基本的区别，就是List可以包含多个相同对象，且是有顺序的，而Set不能有重复，且不保证顺序
 *               （有些实现有顺序，例如LinkedHashSet和SortedSet等）所以Multiset占据了List和Set之间的一个灰色地带：允许重复，但是不保证顺序。 
 * @author phh
 * @date 2016年8月25日
 *
 */
public class MultisetDemo {
	
	/**
	 * 
	 *
	 *Multiset主要方法
　           　Multiset接口定义的接口主要有：
　　　 　 add(E element) :向其中添加单个元素
　　　  　add(E element,int occurrences) : 向其中添加指定个数的元素
　　　  　count(Object element) : 返回给定参数元素的个数
　　　  　remove(E element) : 移除一个元素，其count值 会响应减少
　　　  　remove(E element,int occurrences): 移除相应个数的元素
　　　  　elementSet() : 将不同的元素放入一个Set中
　　　  　entrySet(): 类似与Map.entrySet 返回Set<Multiset.Entry>。包含的Entry支持使用getElement()和getCount()
　　　  　setCount(E element ,int count): 设定某一个元素的重复次数
　　　  　setCount(E element,int oldCount,int newCount): 将符合原有重复个数的元素修改为新的重复次数
　　　  　retainAll(Collection c) : 保留出现在给定集合参数的所有的元素 
　　　  　removeAll(Collectionc) : 去除出现给给定集合参数的所有的元素
	 * @author phh   
	 * @return void
	 */
	public static void testMultsetWordCount(){
        String strWorld="wer|dfd|dd|dfd|dda|de|dr|dr|dr";
        String[] words=strWorld.split("\\|");
        List<String> wordList = Lists.newArrayList();
        for (String word : words) {
            wordList.add(word);
        }
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);
        wordsMultiset.add("phh", 5);
        wordsMultiset.remove("dr", 2);
        wordsMultiset.setCount("phh", 1,4);
        System.out.println("multiset : "+wordsMultiset);
        System.out.println("multiset size : "+wordsMultiset.size());
//        for(String key:wordsMultiset.elementSet()){
//            System.out.println(key+" count："+wordsMultiset.count(key));
//        }
        Iterator<String>  aa = wordsMultiset.iterator();
        while(aa.hasNext()){
        	System.out.println(aa.next());
        }
    }
	
	public static void main(String[] args) {
		testMultsetWordCount();
	}
	
}
