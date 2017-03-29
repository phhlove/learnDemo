package com.phh.learnDemo.java.Enum;

import java.util.EnumSet;

public class EnumSetTest {
	
	public interface Commond{
		void action();
	}
    
	public enum Point{
		UP,DOWN,LEFT,RIGHT
	}
	public static void main(String[] args) {
		EnumSet<Point> pointSet = EnumSet.noneOf(Point.class);
		pointSet.add(Point.UP);
		System.out.println(pointSet);
		pointSet.addAll(EnumSet.of(Point.UP,Point.DOWN, Point.LEFT));
		System.out.println(pointSet);
//		pointSet.remove(Point.UP);
//		System.out.println(pointSet);
		pointSet.retainAll(EnumSet.of(Point.DOWN));
		System.out.println(pointSet);
//		pointSet.
		
		System.out.println(new Commond() {
			
			@Override
			public void action() {
				System.out.println("as");
				
			}
		});
	}
}
