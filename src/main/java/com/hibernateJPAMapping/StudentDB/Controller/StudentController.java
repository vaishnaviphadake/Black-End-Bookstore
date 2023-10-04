package com.hibernateJPAMapping.StudentDB.Controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hibernateJPAMapping.StudentDB.Entity.Student;
import com.hibernateJPAMapping.StudentDB.Repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
//	http://localhost:8080/students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
//	http://localhost:8080/student/3
	@GetMapping("/student/{id}")
	public Optional<Student> getStudents(@PathVariable Long id){
		return studentRepository.findById(id);
	}
	
//	http://localhost:8080/studentName/Vaishnavi
	@GetMapping("/studentName/{firstName}")
	public List<Student> getStudents(@PathVariable String firstName){
		return studentRepository.findByFirstName(firstName);
	}
	
	
//	http://localhost:8080/studentNameContaining/Vaish
	@GetMapping("/studentNameContaining/{firstName}")
	public List<Student> getStudentsContaining(@PathVariable String firstName){
		return studentRepository.findByFirstNameContaining(firstName);
	}
	
//	http://localhost:8080/studentLastNameNotNull
	@GetMapping("/studentLastNameNotNull")
	public List<Student> getStudentsByLastNameNotNull(){
		return studentRepository.findByLastNameNotNull();
	}
	
//	http://localhost:8080/studentByLastName/Phadake
	@GetMapping("/studentByLastName/{lastName}")
	public List<Student> getStudentsByLastName(@PathVariable String lastName){
		return studentRepository.findByLastName(lastName);
	}

	

	
	@GetMapping("firstName/email")
	public String[] getStudentFirstNameByEmailId() {
	return  studentRepository.getStudentFirstNameByemailId("vaishnavi@gmail.com");
	    
	}
	
	//Native
    @GetMapping("nativeId/{Id}")
    public Student getStudentByIDNative(@PathVariable Long Id) {
    	return studentRepository.getStudentByIdNative(Id);
    }
	//Native
	@GetMapping("emailNative/{emailId}")
	public Student getStudentByEmailId(@PathVariable String emailId) {
	return  studentRepository.getStudentByEmailIdNative(emailId);
	    
	}
	//Native Named Parameter
	@GetMapping("emailNativeNamed/{emailId}")
	public Student getStudentByEmailIdNamedParam(@PathVariable String emailId) {
	return  studentRepository.getStudentByEmailIdNativeNamedParam(emailId);
	    
	}
	
//	//Update Query
//	@GetMapping("/updateNameById/{id}/{firstname}")
//	public void updateStudentNative(@PathVariable Long id , @PathVariable String firstname) {
//		 studentRepository.updateStudentnameById(id, firstname);
//		 
//	}
	
	
	
	
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PutMapping("/student/{id}")
	public String updateStudent(@PathVariable Long id,  @RequestBody Student student) {
			if(!studentRepository.existsById(id)) {
				return  "Student with given id not found";
			}
		  Student s1 = studentRepository.getById(id);
		  s1.setFirstName(student.getFirstName());
		  s1.setLastName(student.getLastName());
		  s1.setEmailId(student.getEmailId());
		  s1.setGuardian(student.getGuardian());
		  studentRepository.save(s1);
		  return "Student with given id updated";
		  
	}
	
	@DeleteMapping("/student/{id}")
	public String deleteStudent(@PathVariable Long id) {
		Student s1 = studentRepository.getById(id);
		 studentRepository.delete(s1);
		 return "Student deleted";
	}
	
	@DeleteMapping("/students")
	public String deleteStudents() {
		
		 studentRepository.deleteAll();
		 return "All students deleted";
	}
	
}

