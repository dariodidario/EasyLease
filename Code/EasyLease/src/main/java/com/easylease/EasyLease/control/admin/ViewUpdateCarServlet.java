package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewUpdateCarServlet")
public class ViewUpdateCarServlet extends HttpServlet {
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
            String id=(String) request.getSession().getAttribute("Car_id");

            if(id!=null&&id.equalsIgnoreCase("")==false) {
                CarDAO carDAO = DBCarDAO.getInstance();
                Car car = carDAO.retrieveById(id);
                request.getSession().setAttribute("car_to_update",car);
                User user = (User) request.getSession().getAttribute("user");
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", "admin");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/updateCarJSP.jsp");
                dispatcher.forward(request, response);
            }else {
                User user = (User) request.getSession().getAttribute("user");
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", "admin");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
                dispatcher.forward(request, response);
            }

        }
    }
}
