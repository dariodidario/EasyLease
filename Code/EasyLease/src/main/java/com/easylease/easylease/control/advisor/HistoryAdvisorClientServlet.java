package com.easylease.easylease.control.advisor;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.order.DbOrderDao;
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
  DbOrderDao dbOrderDao = (DbOrderDao) DbOrderDao.getInstance();
  DbEstimateDao dbEstimateDao = (DbEstimateDao) DbEstimateDao.getInstance();

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
        String clientId = request.getParameter("id_client");
        List<Object> list = new ArrayList<>();

        list.addAll(dbOrderDao.retrieveByClient(clientId).stream()
            .filter(o -> o.getEstimate()
                .getAdvisor()
                .getIdUser()
                .equals(advisor.getIdUser()))
            .collect(Collectors.toList()));

        list.addAll(dbEstimateDao.retrieveByClient(clientId).stream()
            .filter(e -> e.getAdvisor()
                .getIdUser()
                .equals(advisor.getIdUser()))
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
