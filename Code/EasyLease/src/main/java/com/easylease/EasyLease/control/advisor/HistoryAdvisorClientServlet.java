package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "HistoryAdvisorClientServlet", value = "/HistoryAdvisorClientServlet")
public class HistoryAdvisorClientServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(
      HistoryAdvisorServlet.class.getName());
  DBOrderDAO dbOrderDao = (DBOrderDAO) DBOrderDAO.getInstance();
  DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();

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
        String clientID = request.getParameter("id_client");
        List<Object> list = new ArrayList<>();

        list.addAll(dbOrderDao.retrieveByClient(clientID).stream()
            .filter(o -> o.getEstimate().getAdvisor().getId().equals(advisor.getId()))
            .collect(Collectors.toList()));

        list.addAll(dbEstimateDao.retrieveByClient(clientID).stream()
            .filter(e -> e.getAdvisor().getId().equals(advisor.getId()))
            .collect(Collectors.toList()));

        request.setAttribute("list", list);
        request.getRequestDispatcher("/advisor/historyAdvisorJSP.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/client/homePageJSP.jsp")
            .forward(request, response);
      }
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
