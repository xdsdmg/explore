package com.zc.explore.model.exception;

public class PwdIncorrectException extends Exception implements ExceptionIF {
  public PwdIncorrectException() {
    super("Password not right.");
  }

  public int getCode() {
    return 1003;
  }
}
