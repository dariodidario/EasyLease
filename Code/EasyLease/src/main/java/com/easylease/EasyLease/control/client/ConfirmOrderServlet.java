package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {
  private DBOrderDAO orderDAO;

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

        request.getRequestDispatcher("/client/confirmOrder.jsp")
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

