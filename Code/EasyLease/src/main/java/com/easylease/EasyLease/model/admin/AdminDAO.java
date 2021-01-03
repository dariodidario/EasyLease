package com.easylease.EasyLease.model.admin;

import com.easylease.EasyLease.model.order.Order;

import java.util.List;

/**
 * Interface that provides the operations that can be performed on the DataBase
 * for the Admin Object.
 *
 * @author Antonio Sarro
 * @version 0.1
 */
public interface AdminDAO {
  /**
   * Search for an Admin based on it's id.
   *
   * @param id of the {@link Admin} you are looking for.
   * @return the {@link Admin} with that id or null if not present in Database.
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  Admin retrieveById(String id);

  /**
   * Search for an Admin based on it's id.
   *
   * @param email of the {@link Admin} you are looking for.
   * @return the {@link Admin} with that id or null if not present in Database.
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  Admin retrieveByEmail(String email);

  /**
   * Search for all {@link Admin}s in the Database.
   *
   * @return a {@link List} of {@link Admin}
   * */
  List<Admin> retrieveAll();
}
