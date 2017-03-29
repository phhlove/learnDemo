package com.phh.learnDemo.guava;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.collect.Range;

/**
 * 
 *
 * @Description: range 区间比较
 * @author phh
 * @date 2016年8月25日
 *
 */
public class RangeDemo {
	/**
	 * 
	 *
	 * span：获取两个range的并集，如果两个range是两连的，则是其最小range
	 * @author phh   
	 * @return void
	 */
	public static void testSpan() {
		System.out.println(Range.closed(3, 5).span(Range.open(5, 10)));
		System.out.println(Range.closed(0, 9).span(Range.closed(3, 4)));
		System.out.println(Range.closed(0, 5).span(Range.closed(3, 9)));
		System.out.println(Range.open(3, 5).span(Range.open(5, 10)));
		System.out.println(Range.closed(1, 5).span(Range.closed(6, 10)));
		System.out.println(Range.closed(1, 5).span(Range.closed(7, 10)));
	}
	public static void main(String[] args) throws Exception {
		String biginDateStr = "2017-01-04 00:00:00";
		String endDateStr = "2017-01-06 00:00:00";
		
		String childBiginDateStr = "2017-01-05 10:00:00";
		String childEndDateStr = "2017-01-06 00:00:00";
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date biginDate = formatter.parse(biginDateStr);
		Date endDate = formatter.parse(endDateStr);
		Date childbiginDate = formatter.parse(childBiginDateStr);
		Date childendDate = formatter.parse(childEndDateStr);
		Range<Date>  ran = Range.closed(biginDate, endDate);
		Range<Date>  childRan = Range.closed(childbiginDate, childendDate);
//		testSpan();
//		BigDecimal a = new BigDecimal(10);
//		BigDecimal b = new BigDecimal("10.0");
//		System.out.println("a:"+a);
//		System.out.println("b:"+b);
//		System.out.println(a.compareTo(b));
//		System.out.println(ran.encloses(childRan));
		String a = "1";
		Object c = a;
		System.out.println(c.getClass());
		
	}
}
