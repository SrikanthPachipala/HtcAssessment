package com.example.service;

import java.util.List;

import com.example.external.ExternalObject;
import com.example.model.Student;

public interface CurdService {
	
	public List<Student> getAllStudents();
	
	public Student addStudent(Student student);
   
	public Student updateStudent(Student student);

	public ExternalObject[] callingExternalApi();
}
