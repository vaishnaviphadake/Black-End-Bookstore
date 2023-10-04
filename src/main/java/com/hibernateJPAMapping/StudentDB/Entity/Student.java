package com.hibernateJPAMapping.StudentDB.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
	name= "student_tbl",
	uniqueConstraints = @UniqueConstraint(
				name ="email_unique",
				columnNames = "Email"
			)
		)
public class Student {
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName ="student_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	private long StudentId;
	
	private String firstName;
	private String lastName;
	
	@Column(name="Email",
			nullable = false
			)
	private String emailId;

	@Embedded
	private Guardian guardian;
	
}
