package com.caching.practise.serviceimpl;

import com.caching.practise.exception.StudentNotFountException;
import com.caching.practise.model.Student;
import com.caching.practise.repository.StudentRepository;
import com.caching.practise.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  /**
   * Method to return all Students
   * @author Aakash-Bhagore
   * @return all Students
   */
  @Override
  @Cacheable(value = "studentCache")
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student saveStudent(Student student) {
    if (student == null && student.getStudentName().isBlank()){
      throw new NullPointerException("Student object is empty");
    }
    student.setStudentDOB(LocalDate.now());
    return studentRepository.saveAndFlush(student);
  }

  @Override
  @CacheEvict(value = "studentCache", key = "#studentId",allEntries = true)
  public void removeStudent(int studentId) {
    studentRepository.deleteById(studentId);
  }

  @Override
  @Cacheable(value = "studentCache", key = "#studentId")
  public Student getStudent(int studentId) {
    Student student = studentRepository.findById(studentId).get();
    if(student == null && student.getStudentName().isBlank()) {
      throw new StudentNotFountException("Student not found for given Id");
    }
    return student;
  }

  @Override
  @CacheEvict(value = "studentCache",allEntries = true)
  public Student updateStudent(Student student) {
    student.setStudentDOB(LocalDate.now());
    return studentRepository.saveAndFlush(student);
  }
}
