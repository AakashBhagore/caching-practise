package com.caching.practise.service;

import com.caching.practise.model.Student;

import java.util.List;

public interface StudentService {

  public List<Student> getAllStudents(); //fetch All Students

  public Student saveStudent(Student student); //save one student

  public void removeStudent(int studentId); //remove student

  public Student getStudent(int studentId); //fetch one student

  public Student updateStudent(Student student); //update existing student
}
