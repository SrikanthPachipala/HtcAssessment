package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

@Repository
public interface CurdRepo extends JpaRepository<Student, Long> {

}
