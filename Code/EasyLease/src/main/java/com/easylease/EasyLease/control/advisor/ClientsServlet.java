package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ClientsServlet", urlPatterns = "/ClientsServlet")
public class ClientsServlet extends HttpServlet {
  DBClientDAO dbClientDAO = (DBClientDAO) DBClientDAO.getInstance();
  DBEstimateDAO estimateDAO = (DBEstimateDAO) DBEstimateDAO.getInstance();

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
        List<Estimate> estimateList = estimateDAO.retrieveByAdvisor(
            advisor.getId());

        dbClientDAO.retrieveAll()
            .forEach(client -> estimateList.forEach(estimate -> {
              if (!clients.containsKey(client)) {
                clients.put(client, false);
              }
              if (estimate.getClient().getId().equals(client.getId())) {
                clients.put(client, true);
              }
            }));
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/advisor/clientsJSP.jsp")
            .forward(request, response);
      } catch (ServletException ex) {
        Logger.getLogger(ClientsServlet.class.getName()).
            log(Level.SEVERE, ex.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp")
            .forward(request, response);
      }
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
