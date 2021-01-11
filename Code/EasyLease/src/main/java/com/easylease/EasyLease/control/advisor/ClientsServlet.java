package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ClientsServlet", urlPatterns = "/ClientsServlet")
public class ClientsServlet extends HttpServlet {
  DBClientDAO dbClientDAO = (DBClientDAO) DBClientDAO.getInstance();

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    Collection<Client> clients = dbClientDAO.retrieveAll();
    request.setAttribute("clients", clients);
    request.getRequestDispatcher("/advisor/clientsJSP.jsp").forward(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
  }
}
