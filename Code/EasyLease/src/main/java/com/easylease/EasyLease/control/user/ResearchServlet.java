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
    List<String> output = new ArrayList<>();
    if (marca != null && !marca.equals("Marca")) {
      List<Car> carList = DBCarDAO.getInstance().retrieveAll();
      carList.removeIf(car -> !car.getVisibility());
      if (tipologia!=null && !tipologia.equals("Tipologia")) {
        carList.removeIf(car -> !car.getType().equals(tipologia));
      }
      for (Car car : carList) {
        if (!output.contains(car.getBrand()) && !car.getBrand().equals(marca)) {
          output.add(car.getBrand());
        }
      }
      output.add(marca);
      output.add("MODELLI");
      carList.removeIf(car-> !car.getBrand().equals(marca));
      for (Car car : carList) {
        output.add(car.getModel());
      }
      output.add("Modello");
    }
    else {
      List<Car> carList = DBCarDAO.getInstance().retrieveByType(tipologia);
      carList.removeIf(car -> !car.getVisibility());
      for (Car car : carList) {
        if (!output.contains(car.getBrand())){
          output.add(car.getBrand());
        }
      }
      output.add("Marca");
    }
    Gson gson = new Gson();
    PrintWriter out = response.getWriter();
    out.print(gson.toJson(output));
    out.flush();
    out.close();
  }

  @Override
  protected void doPost(
      HttpServletRequest req,
      HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

}
