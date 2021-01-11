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

@WebServlet(name = "HomePageServet", value = "/HomePageServlet")
public class HomePageServet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
      String tipologia = request.getParameter("tipologia");
      String marca = request.getParameter("marca");
      String modello = request.getParameter("modello");
      List<Car> carList;
      if ((tipologia == null && marca == null && modello == null) ||
          (tipologia!= null && tipologia.equals("0") && marca !=null &&
              marca.equals("0") && modello!=null && modello.equals("0"))
      ) {
        carList = DBCarDAO.getInstance().retriveAll();
        for (Car c : carList) {
          if (!c.getVisibility()) {
            carList.remove(c);
          }
        }
      }
      else if (modello != null && !modello.equals("0")) {
        carList = new ArrayList<>();
        carList.add(DBCarDAO.getInstance().retriveByModel(modello));
      }
      else if (marca != null && !marca.equals("0")) {
        carList = DBCarDAO.getInstance().retriveByBrand(marca);
        for (Car c : carList) {
          if (!c.getVisibility()) {
            carList.remove(c);
          }
        }
        if (tipologia != null) {
          for (Car t : carList) {
            if (!t.getType().equals(tipologia)) {
              carList.remove(t);
            }
          }
        }
      }
      else {
        carList = DBCarDAO.getInstance().retriveByType(tipologia);
        for (Car c : carList) {
          if (!c.getVisibility()) {
            carList.remove(c);
          }
        }
      }
    request.setAttribute("carList", carList);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/homePageJSP.jsp");
    requestDispatcher.forward(request, response);
  }
}
