package com.easylease.easylease.control.utility;

/**
 * This class defines the methods for generating random alphanumerics id.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
public class IdGenerator {
  /**
   * This method generate a random string (length 5).
   *
   * @return the random id generator
   * @version 0.1
   * @since 0.1
   */
  public static String randomIdGenerator() {
    String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(5);
    for (int i = 0; i < 5; i++) {
      int index = (int) (dictionary.length() * Math.random());
      sb.append(dictionary.charAt(index));
    }
    return sb.toString();
  }
}
