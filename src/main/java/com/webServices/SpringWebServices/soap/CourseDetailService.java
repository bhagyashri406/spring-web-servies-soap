package com.webServices.SpringWebServices.soap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class CourseDetailService {

	//course details - id
	// Course = findById(int id);
	
	// Courses details 

	//DeleteBy Id 
	// Course = deleteById(int id); 
	
	
	public enum Status {
		Success , failure ;
	}
	
	private static List<Course>courseDetails = new ArrayList<Course>();
	
	
	static {
		
		Course course = new Course(2, "name", "discription");
		Course course1 = new Course(3, "name1", "discription1");
		Course course2 = new Course(4, "name2", "discription2");
		Course course3 = new Course(5, "name3", "discription3");
		
		courseDetails.add(course);
		courseDetails.add(course1);
		courseDetails.add(course2);
		courseDetails.add(course3);
	}
		
	public Course FindById(int id) {
		
		for(Course course :courseDetails) {
			if(course.getId()== id) {
				return course ;
			}
		}
		
		return null ;
			
	}
	
	
public List<Course > FindAll(){
		
		return courseDetails ;
			
	}

public Status deleteBYId(int id) {
	
	Iterator<Course> iterator = courseDetails.iterator() ;
	
	while(iterator.hasNext()) {
		Course course = iterator.next() ;
		if(course.getId()== id) {
			iterator.remove();
			return Status.Success;
		}
	}
	
	return Status.failure ;
		
}
}
