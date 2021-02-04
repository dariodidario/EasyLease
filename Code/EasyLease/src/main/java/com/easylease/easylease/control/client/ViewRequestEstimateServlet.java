package com.easylease.easylease.control.client;

import com.easylease.easylease.model.car.DbCarDao;
import com.easylease.easylease.model.optional.DbOptionalDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewRequestEstimateServlet", value = "/ViewRequestEstimateServlet")
public class ViewRequestEstimateServlet extends HttpServlet {
  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String role = (String) request.getSession().getAttribute("role");
    if (!role.equals("client")) {
      request.getRequestDispatcher("/user/homePage.jsp")
          .forward(request, response);
    } else {
      String idCar = request.getParameter("idCar");
      request.getSession()
          .setAttribute("car", DbCarDao.getInstance().retrieveById(idCar));
      request.getSession().setAttribute("optionalCarList",
          DbOptionalDao.getInstance().retrieveByType("Auto"));
      request.getSession().setAttribute("optionalContractList",
          DbOptionalDao.getInstance().retrieveByType("Contratto"));
      request.getRequestDispatcher("/client/requestEstimate.jsp")
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
