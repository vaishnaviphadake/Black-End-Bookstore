package com.hibernateJPAMapping.StudentDB.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernateJPAMapping.StudentDB.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{



}
