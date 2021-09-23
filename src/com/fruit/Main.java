package com.fruit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		List <Fruit> fl =new ArrayList<Fruit>();
		System.out.println("Student List (ordered by name)");
		Fruit s1 = new Fruit(101, "Apple", 1000);
		fl.add(s1);
		Fruit s2 = new Fruit(102, "Orange", 1500);
		fl.add(s2);
		fl.add(new Fruit(103, "Kiwi", 2000));
		fl.add(new Fruit(104, "Grape", 3000));
		fl.add(new Fruit(105, "Fig", 2500));
		fl.add(new Fruit(106, "Lemon", 2200));
		fl.add(new Fruit(107, "Strawberry", 2400));
		fl.add(new Fruit(108, "Pear", 3500));
		fl.add(new Fruit(109, "Peach", 4000));
		fl.add(new Fruit(110, "Plum", 2100));
		

		
		Collections.sort(fl, new FruitComparator());
		for (Fruit s : fl) {
			System.out.println(s.toString());
		}
		
		System.out.println("Student List (reverse ordered by name)");
		
		Collections.sort(fl, new FruitComparatorDesc());
		for(int i = 0; i<fl.size(); i++) {
			System.out.println(fl.get(i).toString());
		}
	}
}
