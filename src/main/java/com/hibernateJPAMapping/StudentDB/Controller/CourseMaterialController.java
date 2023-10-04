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
import com.hibernateJPAMapping.StudentDB.Entity.CourseMaterial;
import com.hibernateJPAMapping.StudentDB.Repository.CourseMaterialRepository;

@RestController
public class CourseMaterialController {
	
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
//	http://localhost:8080/students
	@GetMapping("/courseMaterials")
	public List<CourseMaterial> getCourseMaterials(){
		return courseMaterialRepository.findAll();
	}
	
//	http://localhost:8080/student/3
	@GetMapping("/courseMaterial/{id}")
	public Optional<CourseMaterial> getCourseMaterial(@PathVariable Long id){
		return courseMaterialRepository.findById(id);
	}
	
	@PostMapping("/courseMaterial")
	public CourseMaterial addCourse(@RequestBody CourseMaterial courseMaterial) {
		return courseMaterialRepository.save(courseMaterial);
	}
	
	@PutMapping("/courseMaterial/{id}")
	public String updateCourseMaterial(@PathVariable Long id,  @RequestBody CourseMaterial courseMaterial) {
			if(!courseMaterialRepository.existsById(id)) {
				return  "CourseMaterial with given id not found";
			}
			CourseMaterial cm1 = courseMaterialRepository.getById(id);
		    cm1.setUrl(courseMaterial.getUrl());
//		    cm1.setCourse(courseMaterial.getCourse());
		 
		  courseMaterialRepository.save(cm1);
		
		  return "Course with given id updated";
		  
	}
	
	@DeleteMapping("/courseMaterial/{id}")
	public String deleteCourseMaterial(@PathVariable Long id) {
		CourseMaterial cm1 = courseMaterialRepository.getById(id);
		courseMaterialRepository.delete(cm1);
		 return "CourseMaterial deleted";
	}
	
	@DeleteMapping("/courseMaterials")
	public String deleteCourseMaterials() {
		
		courseMaterialRepository.deleteAll();
		 return "All CourseMaterial deleted";
	}
}
