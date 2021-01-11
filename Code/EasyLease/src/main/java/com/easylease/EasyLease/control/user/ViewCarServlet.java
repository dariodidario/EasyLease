package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewCarServlet")
public class ViewCarServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    DBCarDAO dao = DBCarDAO.getInstance();
    String model = request.getParameter("model");
    request.removeAttribute("model");
    Car car;
    car = dao.retriveByModel(model);
    request.getSession().setAttribute("car", car);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewCarJSP.jsp");
    dispatcher.forward(request, response);
  }
}
