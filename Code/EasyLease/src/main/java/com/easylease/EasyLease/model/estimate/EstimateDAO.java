package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import java.util.List;

/**
 * Interface that provides the operation that can be performed on the DataBase
 * for the Estimate Object.
 *
 * @author Pepe Sara
 * @version 0.1
 */

public interface EstimateDAO {
  /**
   * Search for an Estimate based on its id.
   *
   * @param id of the {@link Estimate} you are looking for.
   * @return the {@link Estimate} with that id or null if not present in Database.
   */
  Estimate retrieveById(String id);

  /**
   * Search for all the Estimates.
   *
   * @return all the {@link Estimate} present in Database.
   */
  List<Estimate>  retrieveAll();

  /**
   * Search for a list of Estimate based on the Advisor who compiles them.
   *
   * @param id of the {@link Advisor} that compiles the list of {@link Estimate}
   *           you are looking for.
   * @return the list of {@link Estimate} that the {@link Advisor} with that id
   *           compiles or null if not present in Database.
   */
  List<Estimate> retrieveByAdvisor(String id);

  /**
   * Search for a list of Estimate based on the Client who required them.
   *
   * @param id of the {@link Client} that required the list of {@link Estimate} you are looking for.
   * @return the list of {@link Estimate} that the {@link Client} with that id
   *         required or null if not present in Database.
   */
  List<Estimate> retrieveByClient(String id);

  /**
   * Update an Estimate.
   *
   * @param e is the {@link Estimate} you want to update.
   */
  void update(Estimate e);

  /**
   * Delete an Estimate by setting its visibility to false.
   *
   * @param e is the {@link Estimate} you want to delete.
   */
  void delete(Estimate e);

  /**
   * Insert an Estimate.
   *
   * @param e is the {@link Estimate} you want to insert.
   */
  void insert(Estimate e);

  /**
   * Delete an Estimate by erasing it from the database.
   *
   * @param e is the {@link Estimate} you want to delete.
   */
  void deleteForever(Estimate e);

  /**
   * Search for an Estimate based on its state.
   *
   * @param state of the {@link Estimate} list you are looking for.
   * @return the {@link Estimate} list with that state or null if not present in Database.
   **/
  List<Estimate> retrieveByState(String state);
}
