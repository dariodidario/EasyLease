package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.admin.Admin;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDao;
import com.easylease.EasyLease.model.car.DbCarDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewUpdateCarServlet", urlPatterns = "/ViewUpdateCarServlet")
public class ViewUpdateCarServlet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String role = (String) request.getSession().getAttribute("role");
    if (role == null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
              "/user/login.jsp");
      dispatcher.forward(request, response);
    } else if (role.equalsIgnoreCase("admin") == false) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
              "/user/login.jsp");
      dispatcher.forward(request, response);
    } else {
      String id = (String) request.getParameter("Car_id");

      if (id != null && id.equalsIgnoreCase("") == false) {
        CarDao carDao = DbCarDao.getInstance();
        Car car = carDao.retrieveById(id);
        request.getSession().setAttribute("car_to_update", car);
        Admin user = (Admin) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/admin/updateCar.jsp");
        dispatcher.forward(request, response);
      } else {
        Admin user = (Admin) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/fragments/error403.jsp");
        dispatcher.forward(request, response);
      }

    }
  }
}
