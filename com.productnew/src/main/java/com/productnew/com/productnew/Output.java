package com.productnew.com.productnew;

import java.util.ArrayList;



public class Output {

public static void main(String[] args) {
		
		ArrayList<Student> list =new ArrayList<Student>();
		//Student student1 = new Student();
		//Student student2 = new Student();
		
		
		//student1.setName("vipin");
		//student2.setName("sagar");
		
		//student1.setRoll("10");
		//student2.setRoll("20");
		
		//list.add(student1);
		//list.add(student2);
		
		
		//list.get(1).setName("rinku");
		
		list.add(new Student("vipin", "10"));
		list.add(new Student("sagar", "20"));
		
		for(Student data:list) {
			System.out.println(data.getName()+" Rolll : "+data.getRoll());
		}
	
		
		
	}
}
