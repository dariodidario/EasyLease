package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomePageServlet", value = "/HomePageServlet")
public class HomePageServlet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      String modello = request.getParameter("modello");
      List<Car> carList = new ArrayList<>();
      if(modello != null && !modello.equals("Modello")){
        carList.add(DBCarDAO.getInstance().retrieveByModel(modello));
      }
      else{
        carList = DBCarDAO.getInstance().retrieveAll();
      }
    request.setAttribute("carList", carList);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/homePageJSP.jsp");
    requestDispatcher.forward(request, response);
  }
}
