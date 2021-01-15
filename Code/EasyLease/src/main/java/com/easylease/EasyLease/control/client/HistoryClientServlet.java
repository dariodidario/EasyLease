package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistoryClientServlet", urlPatterns = "/HistoryClientServlet")
public class HistoryClientServlet extends HttpServlet {

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      if ((request.getSession() == null) || !(session.getAttribute("role").equals("client"))) {
        throw new ServletException("Section dedicated to a registered user "
            + "on the platform correctly as a Client");
      }
      Client client = (Client) session.getAttribute("user");
      String role = (String) session.getAttribute("role");
      if (!(client == null) && role.equals("client")) {
          DBOrderDAO dbOrderDao = (DBOrderDAO) DBOrderDAO.getInstance();
          DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
          List<Object> historyList = new java.util.ArrayList<>();
          List<Estimate> estimateList = dbEstimateDao.retrieveByClient(
              client.getId());
          for (Iterator<Estimate> it = estimateList.iterator(); it.hasNext(); ) {
            Estimate est = it.next();
            if(est.getState().equals("Stipulato")){
              Calendar calendar = new GregorianCalendar();
              calendar.setTime(est.getResponseDate());
              calendar.add(Calendar.DATE, 8);
              if(calendar.before(Calendar.getInstance())){
                est.setState("Non Confermato");
                est.setVisibility(false);
                dbEstimateDao.update(est);
              }
            }
            if (!est.isVisibility()) {
              it.remove();
            }
          }
          List<Order> orderList = dbOrderDao.retrieveByClient(client.getId());
          for (Iterator<Order> ti = orderList.iterator(); ti.hasNext(); ) {
            Order ord = ti.next();
            if(ord.getState().equals("Attesa")){
              Calendar calendar = new GregorianCalendar();
              calendar.setTime(ord.getCreationDate());
              calendar.add(Calendar.DATE, 8);
              System.out.println(calendar.getTime());
              System.out.println(Calendar.getInstance().getTime());
              if(calendar.before(Calendar.getInstance())){
                ord.setState("Non Confermato");
                ord.setVisibility(false);
                dbOrderDao.update(ord);
              }
            }
            if (!ord.isVisibility()) {
              ti.remove();
            }
          }
          historyList.addAll(estimateList);
          historyList.addAll(orderList);
          Iterator<Object> iterator = historyList.iterator();
          request.setAttribute("historyList", historyList);
          request.getRequestDispatcher("/client/historyClientJSP.jsp")
              .forward(request, response);
        } else{
        throw new ServletException();
      }
    } catch (ServletException e) {
      Logger logger = Logger.getLogger(
          EstimateManagementClientServlet.class.getName());
      logger.log(Level.SEVERE, e.getMessage());
      request.getRequestDispatcher("/user/homePageJSP.jsp");
    }
  }
}

