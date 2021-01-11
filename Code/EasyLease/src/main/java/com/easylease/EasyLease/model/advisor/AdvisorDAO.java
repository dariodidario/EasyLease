package com.easylease.EasyLease.model.advisor;


import java.sql.SQLException;
import java.util.List;

/**
 * Interface that provides the operation that can be performed on the DataBase
 * for the Advisor Object.
 *
 * @author Caprio Mattia
 * @since 0.1
 * @version 0.3
 */
public interface AdvisorDAO {
  /**
   * Search for an Advisor based on his id.
   *
   * @param id of the {@link Advisor} you are looking for.
   * @return the {@link Advisor} with that id or null if not present in Database.
   */
  Advisor retrieveById(String id);

  /**
   * Search for an Advisor based on his email.
   *
   * @param email of the {@link Advisor} you are looking for.
   * @return the {@link Advisor} with that email or null if not present in Database.
   */
  Advisor retrieveByEmail(String email);

  /**
   * Search for the hashed password of an Advisor based on his email.
   *
   * @param email of the {@link Advisor} you are looking for.
   * @return the {@link String} of the hashed password with that email
   *      or null if not present in Database.
   */
  String retrievePasswordByEmail(String email) throws SQLException;

  /**
   * Search for all Advisor present in DataBase.
   *
   * @return a List of {@link Advisor} present in Database.
   */
  List<Advisor> retrieveAll();

  /**
   * Inserts the {@link Advisor} that is passed as a parameter in the DataBase.
   *
   * @param advisor {@link Advisor} to insert.
   */
  void update(Advisor advisor);

  /**
   * Updates the {@link Advisor} that is passed as a parameter in the DataBase.
   *
   * @param advisor {@link Advisor} to update.
   */
  void insert(Advisor advisor);

  /**
   * Deletes the {@link Advisor} that is passed as a parameter in the DataBase.
   *
   * @param advisor {@link Advisor} to delete.
   */
  void delete(Advisor advisor);
}
