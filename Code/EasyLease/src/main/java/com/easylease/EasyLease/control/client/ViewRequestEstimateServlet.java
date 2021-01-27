package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.optional.DBOptionalDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewRequestEstimateServlet", value = "/ViewRequestEstimateServlet")
public class ViewRequestEstimateServlet extends HttpServlet {
  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String role = (String) request.getSession().getAttribute("role");
    if ( !role.equals("client") ) {
      request.getRequestDispatcher("/user/homePageJSP.jsp")
          .forward(request, response);
    }
    else {
      String idCar = request.getParameter("idCar");
      request.getSession()
          .setAttribute("car", DBCarDAO.getInstance().retrieveById(idCar));
      request.getSession().setAttribute("optionalCarList",
          DBOptionalDAO.getInstance().retrieveByType("Auto"));
      request.getSession().setAttribute("optionalContractList",
          DBOptionalDAO.getInstance().retrieveByType("Contratto"));
      request.getRequestDispatcher("/client/requestEstimateJSP.jsp")
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
