package com.easylease.EasyLease.model.admin;

import java.util.List;

/**
 * Interface that provides the operations that can be performed on the DataBase
 * for the Admin Object.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
public interface AdminDao {
  /**
   * Search for an Admin based on it's id.
   *
   * @param id of the {@link Admin} you are looking for.
   * @return the {@link Admin} with that id or null if not present in Database.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  Admin retrieveById(String id);

  /**
   * Search for an Admin based on it's id.
   *
   * @param email of the {@link Admin} you are looking for.
   * @return the {@link Admin} with that id or null if not present in Database.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  Admin retrieveByEmail(String email);

  /**
   * Retrieve the hashed psw of Admin from the DB.
   *
   * @param email of the {@link Admin}
   * @return the hashed psw from the DB.
   * @since 0.1
   * @version 0.2
   */
  String retrievePasswordByEmail(String email);

  /**
   * Search for all {@link Admin}s in the Database.
   *
   * @return a {@link List} of {@link Admin}
   * @since 0.1
   * @version 0.2
   */
  List<Admin> retrieveAll();
}
