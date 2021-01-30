package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DbCarDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "HomePageServlet", urlPatterns="/HomePageServlet")

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
    String marca = request.getParameter("marca");
    String tipologia = request.getParameter("tipologia");
    List<Car> carList = new ArrayList<>();
    if(modello != null && !modello.equals("Modello")){
      carList.add(DbCarDao.getInstance().retrieveByModel(modello));
    }
    else if(marca != null && !marca.equals("Marca")){
      carList = DbCarDao.getInstance().retrieveByBrand(marca);
    }
    else if(tipologia != null && !tipologia.equals("Tipologia")) {
      carList = DbCarDao.getInstance().retrieveByType(tipologia);
    }
    else{
      carList = DbCarDao.getInstance().retrieveAll();
    }
    carList.removeIf(c -> !c.getVisibility());
    request.getSession().setAttribute("carList", carList);
    request.getRequestDispatcher("/user/homePage.jsp").forward(request, response);
  }
}
