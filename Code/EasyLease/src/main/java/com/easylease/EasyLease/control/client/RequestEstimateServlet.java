package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.DBOptionalDAO;
import com.easylease.EasyLease.model.optional.Optional;
import com.easylease.EasyLease.model.optional.OptionalDAO;
import com.easylease.EasyLease.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RequestEstimateServlet", value = "/RequestEstimateServlet")
public class RequestEstimateServlet extends HttpServlet {

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    String role = (String) request.getSession().getAttribute("role");
    if (role == null || (role != null && !role.equals("client"))) {
      request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
    }
    else {
      User user = (User) request.getSession().getAttribute("user");
      String carId = request.getParameter("carId");
      String m = request.getParameter("Mesi");

      if (m==null || user == null || carId == null ) {
        request.getRequestDispatcher("/user/homePageJSP.jsp").forward(request, response);
      }

      else {
        int months = Integer.parseInt(m);
        String[] optionals = request.getParameterValues("optionals");
        List<Optional> optionalList = new ArrayList<>();
        if (optionals != null && optionals.length != 0) {
          OptionalDAO optDao = DBOptionalDAO.getInstance();
          for (String optionalId : optionals) {
            optionalList.add(optDao.retrieveById(optionalId));
          }
        }
        Car car = DBCarDAO.getInstance().retrieveById(carId);
        String id = "ES" + IdGenerator.randomIdGenerator();
        while (DBEstimateDAO.getInstance().retrieveById(id) != null) {
          id = "ES" + IdGenerator.randomIdGenerator();
        }

        Estimate estimate = new Estimate(id, 0, (Client) user, null, car,
            months, optionalList, true, "Attesa",
            new Date(System.currentTimeMillis()), null);

        DBEstimateDAO.getInstance().insert(estimate);

        request.getRequestDispatcher("/user/homePageJSP.jsp")
            .forward(request, response);
      }
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
