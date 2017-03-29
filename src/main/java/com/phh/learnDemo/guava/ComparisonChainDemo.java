package com.phh.learnDemo.guava;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class ComparisonChainDemo {

	public class Student implements Comparable<Student> {

		public String name;
		public int age;
		public int score;

		Student(String name, int age, int score) {
			this.name = name;
			this.age = age;
			this.score = score;
		}

		
		 @Override
		 public int hashCode() {
		 return Objects.hashCode(name, age);
		 }

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Student) {
				Student that = (Student) obj;
				return Objects.equal(name, that.name) && Objects.equal(age, that.age) && Objects.equal(score, that.score);
			}
			return false;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).addValue(name).addValue(age).addValue(score).toString();
		}
		@Override
		public int compareTo(Student other) {
			return ComparisonChain.start().compare(age, other.age).compare(score, other.score, Ordering.natural().nullsLast()).result();
		}


	}

	class StudentComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			return ComparisonChain.start().compare(s1.name, s2.name).compare(s1.age, s2.age).compare(s1.score, s2.score).result();
		}
	}

	public static void main(String[] args) {
//		  Student student=new ComparisonChainDemo().new Student("peida",23,80);
//	        Student student1=new ComparisonChainDemo().new Student("aida",23,36);
//	        Student student2=new ComparisonChainDemo().new Student("jerry",24,90);
//	        Student student3=new ComparisonChainDemo().new Student("peida",23,80);
//	        
//	        System.out.println("==========equals===========");
//	        System.out.println(student.equals(student2));
//	        System.out.println(student.equals(student1));
//	        System.out.println(student.equals(student3));
//	        
//	        System.out.println("==========hashCode===========");
//	        System.out.println(student.hashCode());
//	        System.out.println(student1.hashCode());
//	        System.out.println(student3.hashCode());
//	        System.out.println(student2.hashCode());
//	        
//	        System.out.println("==========toString===========");
//	        System.out.println(student.toString());
//	        System.out.println(student1.toString());
//	        System.out.println(student2.toString());
//	        System.out.println(student3.toString());
//	        
//	        System.out.println("==========compareTo===========");
//	        System.out.println(student.compareTo(student1));
//	        System.out.println(student.compareTo(student2));
//	        System.out.println(student2.compareTo(student1));
//	        System.out.println(student2.compareTo(student));
		    Comparator<Student> comparator = new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
//				return tmpMap.get(o1).execSeq() - tmpMap.get(o2).execSeq();
				return ComparisonChain.start().compare(o2.age, o1.age).result();
			}
		    };
		    List<Student> beforeList = Lists.newArrayList();
		    beforeList.add(new ComparisonChainDemo().new Student("peida",23,80));
		    beforeList.add(new ComparisonChainDemo().new Student("qwe",20,80));
		    beforeList.add(new ComparisonChainDemo().new Student("zxc",29,80));
			Collections.sort(beforeList, comparator);
			System.out.println(beforeList);
	}

}
