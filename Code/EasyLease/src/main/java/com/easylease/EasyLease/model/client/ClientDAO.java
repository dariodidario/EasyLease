package com.easylease.EasyLease.model.client;

import java.util.List;

public interface ClientDAO {

  /**
   * This method retrieve a Client by his ID.
   *
   * @param id The Client ID to search for in the database.
   * @return The Client with the corresponding ID.
   */
  Client retrieveById(String id);

  /**
   * This method retrieve a Client by the email in his account.
   *
   * @param email The email to search in the DB.
   * @return The Client with the corresponding email.
   */
  Client retrieveByEmail(String email);

  /**
   * This method retrieve all the Client in the DB.
   *
   * @return A List containing all the Client in the DB.
   */
  List<Client> retrieveAll();

  /**
   * This method insert in the DB a new Client.
   *
   * @param c The new Client which must be inserted into the DB.
   */
  void insert(Client c);

  /**
   * This method update the parameters od an existing Client into the DB.
   *
   * @param c The new Client object containing new parameters to update into the DB.
   */
  void update(Client c);

  /**
   * This method delete a Client from the DB.
   *
   * @param c The Client which has to be deleted from the DB.
   */
  void delete(Client c);
}
