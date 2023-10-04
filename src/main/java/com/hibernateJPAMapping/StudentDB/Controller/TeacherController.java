package com.hibernateJPAMapping.StudentDB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernateJPAMapping.StudentDB.Entity.Teacher;
import com.hibernateJPAMapping.StudentDB.Repository.TeacherRepository;

@RestController
public class TeacherController {

		@Autowired
		private TeacherRepository teacherRepository;
		
		@GetMapping("/teachers")
		public List<Teacher> getAllTeachers(){
			return teacherRepository.findAll();
		}
		
		@PostMapping("/teacher")
		public Teacher addTeacher(@RequestBody Teacher t) {
			return teacherRepository.save(t);
		}
}
