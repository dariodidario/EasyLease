package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResearchServlet", value = "/ResearchServlet")
public class ResearchServlet extends HttpServlet {

  @Override
  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String tipologia = request.getParameter("tipologia");
    String marca = request.getParameter("marca");
    List<String> listaMarche = new ArrayList<>();
    List<String> listaModelli = new ArrayList<>();
    if (tipologia != null && !tipologia.equals("0")) {
      List<Car> carList = DBCarDAO.getInstance().retrieveByType(tipologia);
      carList.removeIf(car -> !car.getVisibility());
      if (marca != null && !marca.equals("0")) {
        for (Car car : carList ) {
          if (car.getBrand().equals(marca)) {
            listaModelli.add(car.getModel());
          }
        }
      }
      else {
        for (Car car : carList) {
          listaModelli.add(car.getModel());
          if (!listaMarche.contains(car.getBrand())) {
            listaMarche.add(car.getBrand());
          }
        }
      }
      listaModelli.add("STOP");
      if (listaMarche.size()>0) {
        listaModelli.addAll(listaMarche);
      }
    }
    Gson gson = new Gson();
    PrintWriter out = response.getWriter();
    out.print(gson.toJson(listaModelli));
    out.flush();
    out.close();
  }

  @Override
  protected void doPost(
      HttpServletRequest req,
      HttpServletResponse resp) throws ServletException, IOException {

  }

}
