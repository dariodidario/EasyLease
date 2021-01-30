package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DbOrderDao;
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

@WebServlet(name = "ConfirmEstimateServlet",
    urlPatterns = "/ConfirmEstimateServlet")
public class ConfirmEstimateServlet extends HttpServlet {
  private DbEstimateDao estimateDao = (DbEstimateDao) DbEstimateDao.getInstance();
  private DbOrderDao orderDao = (DbOrderDao) DbOrderDao.getInstance();

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      if (!(session.getAttribute("user") instanceof Client)) {
        throw new ServletException("Section dedicated to a registered user "
            + "on the platform correctly as a Client");
      }
      String estimateId = request.getParameter("id_estimate");
      String choice = request.getParameter("choice");
      Estimate estimate = estimateDao.retrieveById(estimateId);
      if (choice.equals("Confermato")) {
        estimate.setState("Confermato");
        estimate.setVisibility(false);
        estimateDao.update(estimate);
        Order order = new Order();
        String tryId = "";
        boolean checked = false;
        while (checked == false) {
          tryId = "OR" + IdGenerator.randomIdGenerator();
          if (orderDao.retrieveById(tryId) == null) {
            checked = true;
          }
        }
        order.setId_order("OR" + IdGenerator.randomIdGenerator());
        session.setAttribute("id_order", order.getId_order());
        order.setState("Attesa");
        Date date = new Date();
        order.setStart_date(date);
        order.setEstimate(estimate);
        order.setVisibility(true);
        order.setState("Attesa");
        order.setCreation_date(new Date());
        orderDao.insert(order);
        request.getRequestDispatcher("/HistoryClientServlet")
            .forward(request, response);
      }
      if (choice.equals("Non confermato")) {
        estimate.setState("Non Confermato");
        estimateDao.update(estimate);
        request.getRequestDispatcher("/HistoryClientServlet")
            .forward(request, response);
      }
    } catch (ServletException e) {
      Logger logger = Logger.getLogger(
          ConfirmEstimateServlet.class.getName());
      logger.log(Level.SEVERE, e.getMessage());
      request.getRequestDispatcher("/user/homePage.jsp");
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
