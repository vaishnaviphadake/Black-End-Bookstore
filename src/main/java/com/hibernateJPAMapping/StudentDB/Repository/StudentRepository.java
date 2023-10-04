package com.hibernateJPAMapping.StudentDB.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hibernateJPAMapping.StudentDB.Entity.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student> findByFirstName(String firstName);
	
	List<Student> findByFirstNameContaining(String firstname);
	
	List<Student> findByLastNameNotNull();
	
	List<Student> findByLastName(String lastName);
	
	List<Student> findByGuardianNameContaining(String guardianName);
	
	//JPQL
	@Query("select s from Student s where s.emailId = ?1")
	Student getStudentByemailId(String emailId);
	
	//JPQL
	@Query("select s.firstName from Student s where s.emailId = ?1")
	String[] getStudentFirstNameByemailId(String emailId);
	

	
	//Native Query (SQl)
	@Query(
			value = "select * from student_tbl where student_id = ?1",
			nativeQuery = true
			)
	Student getStudentByIdNative(Long id);
	
	
	//Native Query (SQl)
	@Query(
			value = "select * from student_tbl where email = ?1",
			nativeQuery = true
			)
	Student getStudentByEmailIdNative(String emailId);
	
	//Native Query (SQl) => Named Parameter
	@Query(
			value = "select * from student_tbl where email =:emailId",
			nativeQuery = true
			)
	Student getStudentByEmailIdNativeNamedParam(@Param("emailId") String emailId);
	
	@Modifying
	@Transactional
	@Query(
			 value ="update student_tbl set first_name = ?2 where student_id = ?1;",
			 nativeQuery = true
			)
	void updateStudentnameById(Long id, String firstName);

	

}
