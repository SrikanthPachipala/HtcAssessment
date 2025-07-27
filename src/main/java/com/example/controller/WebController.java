package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Student;

@Controller
public class WebController {


	List<Student> studentList = null;

	public WebController() {
		studentList = new ArrayList<Student>();
		for (int i = 1; i < 300; i++) {
			Student st = new Student();
			Random random = new Random();
			long randomStdId = random.nextLong();
			st.setStdId(randomStdId);
			st.setStdName("Student_" + i);
			st.setStdAddr("Address_" + i);
			studentList.add(st);
		}
	}

	@GetMapping("/")
	public ModelAndView home(@RequestParam(defaultValue = "1") int page) {
		int pageSize = 10;
		int totalPages = (int) Math.ceil((double) studentList.size() / pageSize);
		int start = (page - 1) * pageSize;
		int end = Math.min(start + pageSize, studentList.size());
		List<Student> pageStudents = studentList.subList(start, end);
		ModelAndView mv = new ModelAndView("index");

		mv.addObject("students", pageStudents);
		mv.addObject("currentPage", page);
		mv.addObject("totalPages", totalPages);

		return mv;
	}

}
