package com.easylease.EasyLease.model.optional;

import com.easylease.EasyLease.model.estimate.Estimate;

import java.util.List;

/**
 * Interface that provides the operation that can be performed on the DataBase
 * for the Optional Object.
 *
 * @author Pepe Sara
 * @version 0.1
 */

public interface OptionalDAO {
  /**
   * Search for an Optional based on its id.
   *
   * @param id of the {@link Optional} you are looking for.
   * @return the {@link Optional} with that id or null if not present in Database.
   */
  Optional retrieveById(String id);

  /**
   * Search for a list of Optional based on their type.
   *
   * @param type of the list of {@link Optional} you are looking for.
   * @return the list of {@link Optional} of the type passed or null if not present in Database.
   */
  List<Optional> retrieveByType(String type);

}
