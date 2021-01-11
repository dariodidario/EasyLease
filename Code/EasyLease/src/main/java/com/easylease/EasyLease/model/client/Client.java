package com.easylease.EasyLease.model.client;

import com.easylease.EasyLease.model.user.User;
import java.util.Date;

/**
 * This class provides the information about the client of the EasyLease platform,
 * and also extends the User class, including the parameters id, name, surname, email
 *
 * @since 0.1
 * @author Mattia Mori
 * @version 0.1
 */
public class Client extends User {

  private String birthPlace;
  private Date birthDate;
  /** This instance variable represent the genre of the Client.
   * Can be one between Uomo, Donna, Altro, Preferisco non specificarlo
   * */
  private String kind;
  /** This instance variable represent the client's place of residence.*/
  private String city;
  private String pc;
  private String street;

  /**
   * This is the Constructor of the Client Object.
   *
   * @param birthPlace the birth place of the client
   * @param birthDate the birth date of the client
   * @param kind the genre of the client
   * @param city the client's place of residence
   * @param pc the postal code of the city
   * @param street the street where the client lives
   * @param id the identifier of the client, extended by the class User
   * @param name the name of the client, extended by the class User
   * @param surname the surname of the client, extended by the class User
   * @param email the email of the client, extended by the class User
   */
  public Client(String id, String name, String surname, String email,
                String birthPlace, Date birthDate, String kind,
                String city, String pc, String street) {
    super(id, name, surname, email);
    this.birthPlace = birthPlace;
    this.birthDate = birthDate;
    this.kind = kind;
    this.city = city;
    this.pc = pc;
    this.street = street;
  }

  /**
   * This is the Empty constructor for the Client object.
   */
  public Client(){

  }

  /**
   * Returns the birth place of the client.
   *
   * @return the birth place of the client.
   */
  public String getBirthPlace() {
    return birthPlace;
  }

  /**
   * Set the birth place of the client.
   *
   * @param birthPlace the birth place of the client.
   */
  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  /**
   * Returns the birth date of the client.
   *
   * @return the birth date of the client.
   */
  public Date getBirthDate() {
    return birthDate;
  }

  /**
   * Set the birth date of the client.
   *
   * @param birthDate the birth date of the client.
   */
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Returns the kind of the client.
   *
   * @return the kind of the client.
   */
  public String getKind() {
    return kind;
  }

  /**
   * Set the kind of the client.
   *
   * @param kind the kind of the client.
   */
  public void setKind(String kind) {
    this.kind = kind;
  }

  /**
   * Returns the city of residence of the client.
   *
   * @return the city of residence of the client.
   */
  public String getCity() {
    return city;
  }

  /**
   * Set the city of residence of the client.
   *
   * @param city the city of the client.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Returns the postal code of the client.
   *
   * @return the postal code of the client.
   */
  public String getPc() {
    return pc;
  }

  /**
   * Set the postal code of the client.
   *
   * @param pc the postal code of the client.
   */
  public void setPc(String pc) {
    this.pc = pc;
  }

  /**
   * Returns the street where the client lives.
   *
   * @return the street where the client lives.
   */
  public String getStreet() {
    return street;
  }

  /**
   * Set the street where the client lives.
   *
   * @param street set the street where the client lives.
   */
  public void setStreet(String street) {
    this.street = street;
  }

}
