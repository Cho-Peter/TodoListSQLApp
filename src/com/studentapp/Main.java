package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
	public static void main(String args[]) {
		List <Student> al =new ArrayList<Student>();
		System.out.println("Student List (ordered by name)");
		Student s1 = new Student(101, "Kim", 23);
		al.add(s1);
		Student s2 = new Student(102, "Choi", 21);
		al.add(s2);
		al.add(new Student(103, "Park", 25));
		
		Collections.sort(al);
		for (Student s : al) {
			System.out.println(s.toString());
		}
		
		System.out.println("Student List (reverse ordered by name)");
		
		Collections.sort(al, Collections.reverseOrder());
		for(int i = 0; i<al.size(); i++) {
			System.out.println(al.get(i).toString());
		}
	}
}
