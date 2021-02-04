package com.easylease.easylease.control.admin;

import com.easylease.easylease.model.admin.Admin;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewAddCarServlet", urlPatterns = "/ViewAddCarServlet")
public class ViewAddCarServlet extends HttpServlet {
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
      Admin user = (Admin) request.getSession().getAttribute("user");
      request.getSession().setAttribute("user", user);
      request.getSession().setAttribute("role", "admin");
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
          "/admin/addCar.jsp");
      dispatcher.forward(request, response);
    }
  }
}
