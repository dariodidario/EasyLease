package com.easylease.EasyLease.control.user;


import com.easylease.EasyLease.model.admin.Admin;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.user.DBUserDAO;
import com.easylease.EasyLease.model.user.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

  public void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  public void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    DBUserDAO dao = DBUserDAO.getInstance();
    String email = (String) request.getAttribute("email");
    String password = (String) request.getAttribute("password");


    User user = dao.retrieveByEmail(email);
    if (password.equals(user.getPassword())) {
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
      request.removeAttribute("email");
      request.removeAttribute("password");
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
      dispatcher.forward(request, response);
    }
    request.removeAttribute("email");
    request.removeAttribute("password");
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginJSP.jsp");
    dispatcher.forward(request, response);
  }
}
