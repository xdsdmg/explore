package com.zc.explore.model.exception;

/**
 * EmailDupRegException Email duplicate registration
 */
public class EmailDupRegException extends Exception {
  public EmailDupRegException() {
    super("The current email has been registered.");
  }

  public int getCode() {
    return 1001;
  }
}
