package com.easylease.EasyLease.model.user;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface that provides the operation that can be performed on the DataBase
 * for the User Object.
 *
 * @author Torino Francesco Maria
 * @version 0.1
 */

public interface UserDAO {
  /**
   * Search for an user based on his id.
   *
   * @param Id of the {@link User} you are looking for.
   * @return the {@link User} with that id or null if not present in Database.
   */
  User retrieveById(String Id) throws SQLException;

  /**
   * Search for an user based on his id.
   *
   * @param tipo of the {@link User} you are looking for.
   * @return the {@link User} with that id or null if not present in Database.
   */
  List<User> retrieveByType(String tipo) throws SQLException;

  /**
   * Search for an user based on his id.
   *
   * @param Email of the {@link User} you are looking for.
   * @return the {@link User} with that id or null if not present in Database.
   */
  User retrieveByEmail(String Email) throws SQLException;

  /**
   * Search for all Advisor present in DataBase.
   *
   * @return a List of {@link User} present in Database.
   */
  List<User> retrieveAll() throws SQLException;

  /**
   * Inserts the {@link User} that is passed as a parameter in the DataBase.
   *
   * @param user {@link User} to insert.
   */
  void insert(User user) throws SQLException;

  /**
   * Updates the {@link User} that is passed as a parameter in the DataBase.
   *
   * @param bean {@link User} to update.
   */
  void update(User bean) throws SQLException;

  /**
   * Deletes the {@link User} that is passed as a parameter in the DataBase.
   *
   * @param user {@link User} to delete.
   */
  void delete(User user) throws SQLException;

}
