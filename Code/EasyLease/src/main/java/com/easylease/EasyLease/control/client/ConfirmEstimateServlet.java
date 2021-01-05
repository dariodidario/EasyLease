package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ConfirmEstimateServlet")
public class ConfirmEstimateServlet extends HttpServlet {
  private DBEstimateDAO estimateDao;
  private DBOrderDAO orderDao;

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
        String estimateId = (String) request.getAttribute("id");
        String choice = (String) request.getAttribute("choice");
        Estimate estimate = estimateDao.retrieveById(estimateId);
        if (choice.equals("Confermato")) {
          estimate.setState("Confermato");
          estimateDao.update(estimate);
          Order order = new Order();
          order.setState("Attesa");
          Date date = new Date();
          order.setStartDate(date);
          order.setEstimate(estimate);
          order.setVisibility(true);
          orderDao.insert(order);
        }
        if (choice.equals("Non Confermato")) {
          estimate.setState("Non Confermato");
          estimateDao.update(estimate);
        }
        request.getRequestDispatcher("/client/EstimateManagementClient.jsp")
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
