package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderManagementAdvisorServlet", urlPatterns = "/OrderManagementAdvisorServlet")
public class OrderManagementAdvisorServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(OrderManagementAdvisorServlet.class.getName());

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if(session != null){
      try{
        if (!(session.getAttribute("user") instanceof Advisor)
            || session.getAttribute("user") == null) {
          throw new ServletException("Section dedicated to a registered user"
              + "on the platform correctly as an Advisor");
        }
        String id = request.getParameter("id_order");
        if(id.length() != 7 || !id.startsWith("OR")){
          throw new ServletException("The id sent is incorrect");
        }
        DBOrderDAO dbOrderDAO = (DBOrderDAO) DBOrderDAO.getInstance();
        Order order = dbOrderDAO.retrieveById(id);
        if(order == null){
          throw new ServletException("The order doesn't exist");
        }
        request.setAttribute("order", order);

        request.getRequestDispatcher("/advisor/orderManagementAdvisorJSP.jsp")
            .forward(request, response);
        //TODO cambiare il redirect
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
      }
    }
  }
}
