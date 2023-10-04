package com.hibernateJPAMapping.StudentDB.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernateJPAMapping.StudentDB.Entity.Course;

import com.hibernateJPAMapping.StudentDB.Repository.CourseRepository;

@RestController
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
//	http://localhost:8080/students
	@GetMapping("/courses")
	public List<Course> getStudents(){
		return courseRepository.findAll();
	}
	
//	http://localhost:8080/student/3
	@GetMapping("/course/{id}")
	public Optional<Course> getStudents(@PathVariable Long id){
		return courseRepository.findById(id);
	}
	
	@PostMapping("/course")
	public Course addCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@PutMapping("/course/{id}")
	public String updateCourse(@PathVariable Long id,  @RequestBody Course course) {
			if(!courseRepository.existsById(id)) {
				return  "Course with given id not found";
			}
			Course c1 = courseRepository.getById(id);
		    c1.setTitle(course.getTitle());
		    c1.setCredit(course.getCredit());
		 
		  courseRepository.save(c1);
		
		  return "Course with given id updated";
		  
	}
	
	@DeleteMapping("/course/{id}")
	public String deleteCourse(@PathVariable Long id) {
		Course c1 = courseRepository.getById(id);
		courseRepository.delete(c1);
		 return "Course deleted";
	}
	
	@DeleteMapping("/courses")
	public String deleteCourses() {
		
		courseRepository.deleteAll();
		 return "All Courses deleted";
	}

}
