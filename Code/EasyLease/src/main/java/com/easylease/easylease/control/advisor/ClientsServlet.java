package com.easylease.easylease.control.advisor;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.client.DbClientDao;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.estimate.Estimate;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet that takes care of returning a list of all customers, where
 * the customers of the Advisor calling the servlet are flagged to true.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
@WebServlet(name = "ClientsServlet", urlPatterns = "/ClientsServlet")
public class ClientsServlet extends HttpServlet {
  DbClientDao dbClientDao = (DbClientDao) DbClientDao.getInstance();
  DbEstimateDao estimateDao = (DbEstimateDao) DbEstimateDao.getInstance();

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      try {
        if (!(session.getAttribute("user") instanceof Advisor)
            || session.getAttribute("user") == null) {
          throw new ServletException("Section dedicated to a registered user"
              + "on the platform correctly as an Advisor");
        }

        Advisor advisor = (Advisor) session.getAttribute("user");

        Map<Client, Boolean> clients = new HashMap<>();
        List<Estimate> estimateList = estimateDao.retrieveByAdvisor(
            advisor.getIdUser());

        dbClientDao.retrieveAll()
            .forEach(client -> estimateList.forEach(estimate -> {
              if (!clients.containsKey(client)) {
                clients.put(client, false);
              }
              if (estimate.getClient().getIdUser().equals(client.getIdUser())) {
                clients.put(client, true);
              }
            }));
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/advisor/clients.jsp")
            .forward(request, response);
      } catch (ServletException ex) {
        Logger.getLogger(ClientsServlet.class.getName())
            .log(Level.SEVERE, ex.getMessage());
        request.getRequestDispatcher("/user/homePage.jsp")
            .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/user/homePage.jsp")
          .forward(request, response);
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
