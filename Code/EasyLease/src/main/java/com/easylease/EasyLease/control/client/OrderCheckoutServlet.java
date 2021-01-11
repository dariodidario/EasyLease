package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderCheckoutServlet", value = "/OrderCheckoutServlet")
public class OrderCheckoutServlet extends HttpServlet {
  DBOrderDAO dbOrderDAO = (DBOrderDAO) DBOrderDAO.getInstance();
  Order order;

  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
  }

  @Override
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      if(request.getSession() != null && request.getSession().getAttribute("user") instanceof Client){
        Client client = (Client) request.getSession().getAttribute("user");
        if (client != null) {
          order = dbOrderDAO.retrieveById(request.getParameter("submit"));
          if (order != null) {
              order.setState("Pagato");
              dbOrderDAO.update(order);
          }
          request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
        }
      } else {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "X-Authenticated-Client not available");
      }

  }
}
