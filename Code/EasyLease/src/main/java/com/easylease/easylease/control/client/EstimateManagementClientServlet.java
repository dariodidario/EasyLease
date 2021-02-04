package com.easylease.easylease.control.client;

import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.estimate.Estimate;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EstimateManagementClientServlet",
    urlPatterns = "/EstimateManagementClientServlet")
public class EstimateManagementClientServlet extends HttpServlet {
  private final DbEstimateDao estimateDao = (DbEstimateDao) DbEstimateDao.getInstance();

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      if (!(session.getAttribute("user") instanceof Client)) {
        throw new ServletException("Section dedicated to a registered user "
            + "on the platform correctly as a Client");
      }
      String id = request.getParameter("id_estimate");
      if (id.length() != 7 || !id.startsWith("ES")) {
        throw new ServletException("Section dedicated to a registered user "
            + "on the platform correctly as a Client");
      }
      Estimate estimate = estimateDao.retrieveById(id);
      request.setAttribute("estimate", estimate);
      request.getRequestDispatcher("/client/estimateManagementClient.jsp")
          .forward(request, response);
    } catch (ServletException e) {
      Logger logger = Logger.getLogger(
          EstimateManagementClientServlet.class.getName());
      logger.log(Level.SEVERE, e.getMessage());
      request.getRequestDispatcher("/user/homePage.jsp").forward(request, response);
    }
  }
}
