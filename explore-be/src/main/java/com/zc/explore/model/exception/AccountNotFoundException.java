package com.zc.explore.model.exception;

/**
 * AccountNotFoundException
 */
public class AccountNotFoundException extends Exception implements ExceptionIF {
  public AccountNotFoundException() {
    super("Account not found.");
  }

  public int getCode() {
    return 1002;
  }
}
