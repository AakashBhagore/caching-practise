package com.caching.practise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFountException extends RuntimeException {

  public StudentNotFountException() {
  }

  public StudentNotFountException(String message) {
    super(message);
  }

}
