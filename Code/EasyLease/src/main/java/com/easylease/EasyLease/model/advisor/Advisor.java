package com.easylease.EasyLease.model.advisor;


import com.easylease.EasyLease.model.user.User;
import java.util.Date;

/**
 * This class models an object of type Advisor within the system.
 *
 * @author Caprio Mattia
 * @since 0.1
 * @version 0.2
 */
public class Advisor extends User {

  /** Rapresents the date from which the Advisor was hired. */
  private Date hireDate;

  /**
   * Empty constructor for the Advisor object.
   */
  public Advisor() {
    super();
  }

  /**
   * Constructor for the Advisor object.
   *
   * @param id The ID who identify the Advisor.
   * @param name The name of the Advisor.
   * @param surname The surname of the Advisor.
   * @param email The email of the Advisor.
   * @param hireDate The date from which the Advisor was hired.
   */
  public Advisor(String id, String name, String surname, String email, Date hireDate) {
    super(id, name, surname, email);
    this.hireDate = hireDate;
  }

  /**
   * Get the Date from which the Advisor was hired.
   *
   * @return Date from which the Advisor was hired.
   */
  public Date getHireDate() {
    return hireDate;
  }

  /**
   * Set the Date from which the Advisor was hired.
   *
   * @param hireDate The Date from which the Advisor was hired.
   */
  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

}
