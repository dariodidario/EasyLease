package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistoryAdvisorServlet" , urlPatterns = "/HistoryAdvisorServlet")

public class HistoryAdvisorServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(HistoryAdvisorServlet.class.getName());

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
        DBOrderDAO dbOrderDao = (DBOrderDAO) DBOrderDAO.getInstance();
        DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
        List<Object> list = new ArrayList<>();
        List<Order> orders = dbOrderDao.retrieveByAdvisor(advisor.getId()); //ADJdybc
        for(Order o : orders)
          if(o.isVisibility())
            list.add(o);
        List<Estimate> estimates = dbEstimateDao.retrieveByAdvisor(advisor.getId()); //ADJdybc
        for(Estimate e : estimates){

          if(e.isVisibility())
            list.add(e);

          }
        request.setAttribute("list", list);
        request.getRequestDispatcher("/advisor/historyAdvisorJSP.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/client/homePageJSP.jsp").forward(request, response);
      }
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
   doPost(request, response);
  }
}
