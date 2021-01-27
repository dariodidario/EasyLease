package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that takes care to show at the Advisor only the Orders
 * of a particular customer.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
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
            .filter(o -> o.getEstimate()
                .getAdvisor()
                .getId()
                .equals(advisor.getId()))
            .collect(Collectors.toList()));

        list.addAll(dbEstimateDao.retrieveByClient(clientID).stream()
            .filter(e -> e.getAdvisor()
                .getId()
                .equals(advisor.getId()))
            .collect(Collectors.toList()));

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
