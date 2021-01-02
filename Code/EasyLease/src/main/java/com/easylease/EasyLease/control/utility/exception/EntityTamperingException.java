package com.easylease.EasyLease.control.utility.exception;

/**
 * This Exception is thrown when tampering with any entity in the database occurs.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class EntityTamperingException extends RuntimeException {
  public EntityTamperingException() {
    super();
  }

  public EntityTamperingException(String message) {
    super(message);
  }
}
