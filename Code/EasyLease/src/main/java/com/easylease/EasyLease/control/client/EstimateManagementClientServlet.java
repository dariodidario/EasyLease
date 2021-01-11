package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
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
  private final DBEstimateDAO estimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
  private final DBClientDAO clDao = (DBClientDAO) DBClientDAO.getInstance();

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (!(session == null)) {
      try {
        if (!(session.getAttribute("user") instanceof Client)
            || session.getAttribute("user") == null) {
          throw new ServletException("Section dedicated to a registered user "
              + "on the platform correctly as a Client");
        }
        Client currentClient = (Client) session.getAttribute("user");
        String id = request.getParameter("id_estimate");
        //Client client = clDao.retrieveById("CLEE8BD");
        //Client client = clDao.retrieveById("CLcapNK");
        String role = (String) request.getSession().getAttribute("role");
        //role = "Cliente";
        if (id.length() != 7 || !id.startsWith("ES")) {
          throw new ServletException("Section dedicated to a registered user "
              + "on the platform correctly as a Client");
        }
        Estimate estimate = estimateDao.retrieveById(id);
        request.setAttribute("estimate", estimate);
        request.getRequestDispatcher("/client/estimateManagementClientJSP.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        Logger logger = Logger.getLogger(
            EstimateManagementClientServlet.class.getName());
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp");
      }
    }
  }
}
