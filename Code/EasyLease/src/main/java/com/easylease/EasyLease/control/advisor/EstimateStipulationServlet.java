package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.Optional;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Servlet has the task of stipulate an estimate with the value given by the Advisor.
 *
 * @author Caprio Mattia
 * @version 0.7
 * @since 0.1
 */
@WebServlet(name = "EstimateStipulationServlet", value = "/EstimateStipulationServlet")

public class EstimateStipulationServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(
      EstimateStipulationServlet.class.getName());

  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      try {
        if (!(session.getAttribute("user") instanceof Advisor)) {
          throw new ServletException("Section dedicated to a registered user"
              + " on the platform correctly as an Advisor");
        }
        String id = request.getParameter("id");
        if (id == null || id.length() != 7 || !id.startsWith("ES")) {
          throw new ServletException("The id sent is incorrect");
        }
        DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
        Estimate estimate = dbEstimateDao.retrieveById(id);
        if (estimate == null) {
          throw new ServletException("The estimate doesn't exist");
        }
        if (!estimate.getState().equals("Preso in carico")
            || (Boolean) session.getAttribute("stipulation") != true) {
          throw new ServletException(
              "The chosen estimate cannot be stipulated");
        }
        estimate.setResponseDate(new Date());
        estimate.setPrice(0);
        for (Optional o : estimate.getOptionalList()) {
          o.setPrice(Float.parseFloat(request.getParameter(o.getName())));
          estimate.setPrice(estimate.getPrice() + o.getPrice());
        }
        estimate.setPrice(estimate.getPrice()
            + estimate.getCar().getPrice() * estimate.getPeriod());
        estimate.setState("Stipulato");
        dbEstimateDao.update(estimate);
        request.setAttribute("estimate", estimate);
        session.removeAttribute("stipulation");
        request.getRequestDispatcher(
            "/advisor/estimateManagementAdvisorJSP.jsp")
            .forward(request, response);
      } catch (ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp")
            .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/user/homePageJSP.jsp")
          .forward(request, response);
    }
  }

  @Override
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}

