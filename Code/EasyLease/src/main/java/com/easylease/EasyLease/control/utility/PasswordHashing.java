package com.easylease.EasyLease.control.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines the methods for hashing password.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class PasswordHashing {

  private static final Logger logger = Logger.getLogger(PasswordHashing.class.getName());

  /**
   * Method to generate the SHA-1 of the password.
   *
   * @param password of which to hash.
   * @return the hash of password.
   */
  public static String generatePassword(String password, String algorithm) {

 
    try {
      MessageDigest md = MessageDigest.getInstance(algorithm);
      md.update(password.getBytes(StandardCharsets.UTF_8));
      return toHex(md.digest());
    } catch (NoSuchAlgorithmException ex) {
        logger.log(Level.SEVERE, ex.getMessage());
        return null;
    }
  }

  /**
   * Method to check if the password is correct.
   *
   * @param password entered by the client.
   * @param hash of client retrieved from the Database.
   * @return true if correct, false if wrong.
   */

  public static boolean passwordAuthenticator(String password, String hash, String algorithm) {
    return hash.equals(generatePassword(password, algorithm));
  }

  /**
   * Models the byte array of the hash into a string.
   *
   * @param data the result of the password hashing.
   * @return the hash string.
   */
  private static String toHex(byte[] data) {
    StringBuilder sb = new StringBuilder();
    for (byte b : data) {
      String digit = Integer.toString(b & 0xFF, 16);
      if (digit.length() == 1) {
        sb.append("0");
      }
      sb.append(digit);
    }
    return sb.toString();
  }
}
