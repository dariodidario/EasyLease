package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EstimateManagementAdvisorServlet",
    urlPatterns = "/EstimateManagementAdvisorServlet")

/**
 * @since 0.1
 * @version 0.5
 * @author Caprio Mattia
 */
public class EstimateManagementAdvisorServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(EstimateManagementAdvisorServlet.class.getName());

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
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
        String id = request.getParameter("id_estimate");
        if (id.length() != 7 || !id.startsWith("ES")) {
          throw new ServletException("The id sent is incorrect");
        }
        DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
        Estimate estimate = dbEstimateDao.retrieveById(id);
        if (estimate == null) {
          throw new ServletException("The estimate doesn't exist");
        }
        if (estimate.getState().equals("Richiesto")) {
          estimate.setAdvisor((Advisor) session.getAttribute("user"));
          estimate.setState("Preso in carico");
        }
        dbEstimateDao.update(estimate);
        request.setAttribute("estimate", estimate);
        request.getRequestDispatcher("/advisor/estimateManagementAdvisorJSP.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
      }
    }
  }
}
