package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.control.utility.PasswordHashing;
import com.easylease.EasyLease.model.admin.DBAdminDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

  public void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  public void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    DBAdminDAO adminDao = (DBAdminDAO) DBAdminDAO.getInstance();
    DBAdvisorDAO advisorDao = (DBAdvisorDAO) DBAdvisorDAO.getInstance();
    DBClientDAO clientDao = (DBClientDAO) DBClientDAO.getInstance();
    String email = (String) request.getParameter("userEmail");
    String password = (String) request.getParameter("userPassword");
    try {
      String passwordAd = adminDao.retrievePasswordByEmail(email);
      if (passwordAd != null) {
        if (PasswordHashing.passwordAuthenticator(password, passwordAd, "SHA-1")) {
          request.getSession().setAttribute("role", "admin");
          request.getSession().setAttribute("user", adminDao.retrieveByEmail(email));
          request.removeAttribute("userEmail");
          request.removeAttribute("userPassword");
          request.getRequestDispatcher("/user/homePage.jsp")
              .forward(request, response);
        }
        else {
          request.removeAttribute("userEmail");
          request.removeAttribute("userPassword");
          request.getRequestDispatcher("/user/login.jsp")
              .forward(request, response);
        }
      }
      else {
        String passwordAdv = advisorDao.retrievePasswordByEmail(email);
        if (passwordAdv != null) {
          if (PasswordHashing.passwordAuthenticator(password, passwordAdv, "SHA-1")) {
            request.getSession().setAttribute("role", "advisor");
            request.getSession().setAttribute("user", advisorDao.retrieveByEmail(email));
            request.removeAttribute("userEmail");
            request.removeAttribute("userPassword");
            request.getRequestDispatcher("/user/homePage.jsp")
                .forward(request, response);
          }
          else {
            request.removeAttribute("userEmail");
            request.removeAttribute("userPassword");
            request.getRequestDispatcher("/user/login.jsp")
                .forward(request, response);
          }
        }
        else {
          String passwordCl = clientDao.retrievePasswordByEmail(email);
          if (passwordCl != null) {
            if (PasswordHashing.passwordAuthenticator(password, passwordCl, "SHA-1")) {
              request.getSession().setAttribute("role", "client");
              request.getSession().setAttribute("user", clientDao.retrieveByEmail(email));
              request.removeAttribute("userEmail");
              request.removeAttribute("userPassword");
              request.getRequestDispatcher("/user/homePage.jsp")
                  .forward(request, response);
            }
            else {
              request.removeAttribute("userEmail");
              request.removeAttribute("userPassword");
              request.getRequestDispatcher("/user/login.jsp")
                  .forward(request, response);
            }

          }
          else {
            request.removeAttribute("userEmail");
            request.removeAttribute("userPassword");
            request.getRequestDispatcher("/user/login.jsp")
                .forward(request, response);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}