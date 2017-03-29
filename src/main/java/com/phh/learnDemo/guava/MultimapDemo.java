package com.phh.learnDemo.guava;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * 
 *
 * @Description: Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构。
 * 让我们可以简单优雅的实现上面复杂的数据结构，让我们的精力和时间放在实现业务逻辑上，而不是在数据结构上
 * @author phh
 * @date 2016年8月25日
 *
 */
public class MultimapDemo {
	class StudentScore{
	    int CourseId;
	    int score;
	}
    public static  void teststuScoreMultimap(){
        Multimap<String,StudentScore> scoreMultimap = ArrayListMultimap.create(); 
        for(int i=10;i<20;i++){
            StudentScore studentScore=new MultimapDemo().new StudentScore();
            studentScore.CourseId=1001+i;
            studentScore.score=100-i;
            scoreMultimap.put("peida",studentScore);
        }
        System.out.println("scoreMultimap:"+scoreMultimap);
        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.keys());
        List<StudentScore> aa = (List<StudentScore>) scoreMultimap.get("peida");
//        Map<String, Collection<StudentScore>> b = scoreMultimap.asMap();
        for (StudentScore studentScore : aa) {
			System.out.println(studentScore.score);
		}
    }
    
    public static void main(String[] args) {
    	teststuScoreMultimap();
	}
}
