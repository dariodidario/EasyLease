package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import java.io.IOException;
import java.util.Collections;
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
  private DBEstimateDAO estimateDao;
  private DBOrderDAO orderDao;
  private final DBClientDAO clDao = (DBClientDAO) DBClientDAO.getInstance();

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (!(session == null)) {
      try {
        if (!(session.getAttribute("role").equals("Client"))
            || session.getAttribute("role") == null) {
          throw new ServletException("Section dedicated to a registered user "
              + "on the platform correctly as a Client");
        }
        Client client = (Client) request.getSession().getAttribute("user");
        /*Client client = clDao.retrieveById("CLEE8BD");
        Client client = clDao.retrieveById("CLcapNK");*/
        String role = (String) request.getSession().getAttribute("role");
        //role = "Cliente";
        if (!(client == null) && role.equals("Cliente")) {
          try {
            DBOrderDAO dbOrderDao = (DBOrderDAO) DBOrderDAO.getInstance();
            DBEstimateDAO dbEstimateDao = (DBEstimateDAO) DBEstimateDAO.getInstance();
            List<Object> historyList = new java.util.ArrayList<>();
            List<Estimate> estimateList = dbEstimateDao.retrieveByClient(
                client.getId());
            for (Iterator<Estimate> it = estimateList.iterator(); it.hasNext(); ) {
              Estimate item = it.next();
              if (!item.isVisibility()) {
                it.remove();
              }
            }
            List<Order> orderList = dbOrderDao.retrieveByClient(client.getId());
            for (Iterator<Order> ti = orderList.iterator(); ti.hasNext(); ) {
              Order meti = ti.next();
              if (!meti.isVisibility()) {
                ti.remove();
              }
            }
            historyList.addAll(estimateList);
            historyList.addAll(orderList);
            Iterator<Object> iterator = historyList.iterator();
            request.setAttribute("historyList", historyList);
            request.getRequestDispatcher("/client/historyClientJSP.jsp")
                .forward(request, response);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (ServletException e) {
        Logger logger = Logger.getLogger(
            EstimateManagementClientServlet.class.getName());
        logger.log(Level.SEVERE, e.getMessage());
        request.getRequestDispatcher("/user/homePageJSP.jsp");
      }
    }
  }
}
