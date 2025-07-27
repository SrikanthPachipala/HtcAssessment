package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.external.ExternalObject;
import com.example.model.Student;
import com.example.service.CurdService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CurdOperationController {

	private static final Logger logger = LoggerFactory.getLogger(CurdOperationController.class);

	@Autowired
	private CurdService curdService;
	
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudents() {
		logger.info("GET /student - Start fetching all students");
		List<Student> studentsList = curdService.getAllStudents();
		return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
		
	}

	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		logger.info("Post /addstudent - Add New Student");
		
		if(student!=null) {
			String status = null;
			Student std = curdService.addStudent(student);
			if (std.getStdId() != null) {
				status = "Student Saved Succesfully and Student Id is " + std.getStdId();
			} else {
				status = "Student not Saved";
			}
			return new ResponseEntity<String>(status, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Unable to save", HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateStudent(@RequestBody Student student) {
		logger.info("PUT /update - Updating the student");
		Student std = curdService.updateStudent(student);
		if (std != null) {
			return new ResponseEntity<Student>(std, HttpStatus.OK);
		} else {

		}
		return new ResponseEntity<String>("Student Is Not Fuound ", HttpStatus.OK);
	}

	@GetMapping("/external/api")
	public ResponseEntity<?> callingExternalApi() {
		logger.info("GET /external/api - All the thrid party api from local");
		ExternalObject[] data = curdService.callingExternalApi();
		if (data != null) {
			return new ResponseEntity<ExternalObject[]>(data, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Data No Found", HttpStatus.OK);
	}

}
