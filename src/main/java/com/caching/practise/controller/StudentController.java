package com.caching.practise.controller;

import com.caching.practise.model.Student;
import com.caching.practise.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping(value = "/students")
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  //get one student
  @GetMapping(value = "/student/{studentId}")
  public String getStudent(@PathVariable int studentId) {
    try {
      Student student = studentService.getStudent(studentId);
      return "Student with Id "+student;
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Something went wrong";
    }
  }

  //delete one student
  @DeleteMapping(value = "/student/{studentId}")
  public String deleteStudent(@PathVariable int studentId) {
    try {
      studentService.removeStudent(studentId);
      return "Deleted Successfully";
    } catch (Exception e) {
      e.getStackTrace();
      return "Something Went wrong";
    }
  }

  //save student
  @PostMapping(value = "/student")
  public Student saveStudent(@RequestBody Student student) {
    return studentService.saveStudent(student);
  }

  //update student
  @PutMapping(value = "/student")
  public Student updateStudent(@RequestBody Student student) {
    return studentService.updateStudent(student);
  }
}
