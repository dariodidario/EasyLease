package com.easylease.easylease.control.advisor;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.estimate.Estimate;
import com.easylease.easylease.model.order.DbOrderDao;
import com.easylease.easylease.model.order.Order;
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

/**
 * @author Caprio Mattia
 * @version 0.7
 * @since 0.1
 */
@WebServlet(name = "HistoryAdvisorServlet", urlPatterns = "/HistoryAdvisorServlet")

public class HistoryAdvisorServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(
      HistoryAdvisorServlet.class.getName());

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      try {
        if (!(session.getAttribute("user") instanceof Advisor)) {
          throw new ServletException("Section dedicated to a registered user"
              + " on the platform correctly as an Advisor");
        }
        Advisor advisor = (Advisor) session.getAttribute("user");
        DbOrderDao dbOrderDao = (DbOrderDao) DbOrderDao.getInstance();
        DbEstimateDao dbEstimateDao = (DbEstimateDao) DbEstimateDao.getInstance();
        List<Object> list = new ArrayList<>();
        for (Order o : dbOrderDao.retrieveByAdvisor(advisor.getIdUser())) {
          if (o.isVisibility()) {
            list.add(o);
          }
        }
        list.addAll(dbEstimateDao.retrieveByAdvisor("ADfake0"));
        for (Estimate e : dbEstimateDao.retrieveByAdvisor(advisor.getIdUser())) {
          if (e.isVisibility()) {
            list.add(e);
          }
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("/advisor/historyAdvisor.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePage.jsp")
            .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/user/homePage.jsp")
          .forward(request, response);
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
