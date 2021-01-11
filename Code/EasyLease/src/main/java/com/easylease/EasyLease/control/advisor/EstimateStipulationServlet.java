package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.Optional;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EstimateStipulationServlet", value = "/EstimateStipulationServlet")
public class EstimateStipulationServlet extends HttpServlet {
  private final Logger logger = Logger.getLogger(EstimateStipulationServlet.class.getName());
  @Override
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
        String id = request.getParameter("id");
        if(id.length() != 7 || !id.startsWith("ES")){
          throw new ServletException("The id sent is incorrect");
        }
        DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
        Estimate estimate = dbEstimateDao.retrieveById(id);
        if(estimate == null){
          throw new ServletException("The estimate doesn't exist");
        }
        if(!estimate.getState().equals("Preso in carico")){
          throw new ServletException("The chosen estimate cannot be stipulated");
        }
        estimate.setResponseDate(new Date());
        estimate.setPrice(0);
        for(Optional o : estimate.getOptionalList()){
          o.setPrice(Float.parseFloat(request.getParameter(o.getName())));
          estimate.setPrice(estimate.getPrice() + o.getPrice());
        }
        estimate.setPrice(estimate.getPrice()
            + estimate.getCar().getPrice() * estimate.getPeriod());
        estimate.setState("Stipulato");
        dbEstimateDao.update(estimate);
        //TODO chiamare funzione che aggiorna il prezzo degli optional
        //TODO cambiare la redirect dopo aver aggiunto i controlli sulla jsp
        request.getRequestDispatcher("/EstimateManagementAdvisorServlet")
            .forward(request, response);
      } catch(ServletException e) {
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
      }
    }
  }


  @Override
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}

