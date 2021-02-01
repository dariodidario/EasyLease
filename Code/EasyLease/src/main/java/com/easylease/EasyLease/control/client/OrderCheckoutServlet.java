package com.easylease.EasyLease.control.client;


import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DbOrderDao;
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

/**
 * Servlet that takes care of setting an Order to "Pagato" after
 * the checkout process.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
@WebServlet(name = "OrderCheckoutServlet", value = "/OrderCheckoutServlet")
public class OrderCheckoutServlet extends HttpServlet {
  DbOrderDao dbOrderDao = (DbOrderDao) DbOrderDao.getInstance();
  Order order;

  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      try {
        if (!(session.getAttribute("user") instanceof Client)
            || session.getAttribute("user") == null) {
          throw new ServletException("Section dedicated to a registered user"
              + "on the platform correctly as a Client");
        }
        Client client = (Client) request.getSession().getAttribute("user");

        if (client != null) {
          order = dbOrderDao.retrieveById(request.getParameter("submit"));
          if (order != null) {
            if (order.getState().equals("Confermato")) {
              order.setState("Pagato");
              dbOrderDao.update(order);
            }
          }
        }
        request.getRequestDispatcher("/user/homePage.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        Logger.getLogger(OrderCheckoutServlet.class.getName())
            .log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePage.jsp")
            .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/user/homePage.jsp")
          .forward(request, response);
    }
  }
}