package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewAddAdvisorServlet", urlPatterns = "/ViewAddAdvisorServlet")
public class ViewAddAdvisorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role =(String) request.getSession().getAttribute("role");
        if (role == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
            dispatcher.forward(request, response);
        } else if (role.equalsIgnoreCase("admin") == false) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
            dispatcher.forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", "admin");
            request.getSession().setAttribute("error", "");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
            dispatcher.forward(request, response);
        }
    }
}
