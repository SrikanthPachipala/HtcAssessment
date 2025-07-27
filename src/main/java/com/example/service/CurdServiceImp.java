package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.external.ExternalObject;
import com.example.model.Student;
import com.example.repo.CurdRepo;

@Service
public class CurdServiceImp implements CurdService {

	@Autowired
	private CurdRepo curdRepo;
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@Value("${external.api.url}")
    private String externalApiUrl;


	@Override
	public List<Student> getAllStudents() {
		List<Student> allStds = curdRepo.findAll();
		allStds.size();
		if (allStds.size() > 0) {
			return allStds;
		} else {
			return null;
		}

	}

	@Override
	public Student addStudent(Student student) {
		Student std = curdRepo.save(student);
		return std;
	}

	@Override
	public Student updateStudent(Student student) {
		Optional<Student> std = curdRepo.findById(student.getStdId());

		if (std.isPresent()) {
			Student existingStudent = std.get();
			existingStudent.setStdName(student.getStdName());
			existingStudent.setStdAddr(student.getStdAddr());
			return curdRepo.save(existingStudent);

		} else {
			return null;
		}

	}

	@Override
	public ExternalObject[] callingExternalApi() {
		
		ExternalObject[] thirdpartyData = restTemplate.getForObject(externalApiUrl, ExternalObject[].class);
		if(thirdpartyData!=null) {
			return thirdpartyData; 
		}
		return null;
	}

}
