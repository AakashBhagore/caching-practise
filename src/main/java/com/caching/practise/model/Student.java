package com.caching.practise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int studentId;
   private String studentName;
   private LocalDate studentDOB;
   private String studentQuote;
}
