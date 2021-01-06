package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.admin.Admin;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.user.DBUserDAO;
import com.easylease.EasyLease.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    boolean log = false;
    DBUserDAO dao = DBUserDAO.getInstance();
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    request.removeAttribute("email");
    request.removeAttribute("password");

    User user = dao.retrieveByEmail(email);
    if (password.equals(user.getPassword())) {
      log = true;
      request.getSession().setAttribute("user", user);
      if (user instanceof Admin) {
        request.getSession().setAttribute("role", "admin");
      }
      if (user instanceof Advisor) {
        request.getSession().setAttribute("role", "advisor");
      }
      if (user instanceof Client) {
        request.getSession().setAttribute("role", "client");
      }
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
      dispatcher.forward(request, response);
    }
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginJSP.jsp");
    dispatcher.forward(request, response);
  }
}
