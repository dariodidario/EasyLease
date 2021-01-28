package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderManagementClientServlet", urlPatterns = "/OrderManagementClientServlet")
public class OrderManagementClientServlet extends HttpServlet {

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
      DBOrderDAO orderDao = (DBOrderDAO) DBOrderDAO.getInstance();
      String id = request.getParameter("id_order");
      System.out.println(id);
      if (id.length() != 7 || !id.startsWith("OR")) {
        throw new ServletException("Wrong id");
      }
      Order order = orderDao.retrieveById(id);
      request.setAttribute("order", order);
      request.getRequestDispatcher("/client/orderManagementClientJSP.jsp")
          .forward(request, response);
    } catch (ServletException e) {
      Logger logger = Logger.getLogger(
          OrderManagementClientServlet.class.getName());
      logger.log(Level.SEVERE, e.getMessage());
      request.getRequestDispatcher("/user/homePageJSP.jsp");
    }
  }
}
