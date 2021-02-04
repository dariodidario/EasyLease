package com.easylease.easylease.control.admin;


import com.easylease.easylease.model.admin.Admin;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.car.CarDao;
import com.easylease.easylease.model.car.DbCarDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**this servlet provides to delete a car from the .*/

@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {

  protected void doPost(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    CarDao CarDao = DbCarDao.getInstance();
    String role = (String) request.getSession().getAttribute("role");
    if (role == null) {
      RequestDispatcher dispatcher =
          getServletContext().getRequestDispatcher("/user/login.jsp");
      dispatcher.forward(request, response);
    } else if (role.equalsIgnoreCase("admin") == false) {
      RequestDispatcher dispatcher =
          getServletContext().getRequestDispatcher("/user/login.jsp");
      dispatcher.forward(request, response);
    } else {

      String id = request.getParameter("ID_Delete");
      if (id != null && !id.equalsIgnoreCase("")) {
        Car car = CarDao.retrieveById(id);
        car.setVisibility(false);
        CarDao.update(car);
        File canc = new File(request.getServletContext().getRealPath("img"));
        File rem = new File(canc, car.getImage());
        Files.delete(rem.toPath());

        Admin user = (Admin) request.getSession().getAttribute("user");

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");
        request.getSession().setAttribute("carList", null);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Auto eliminata con successo');");
        out.println("location='user/homePage.jsp';");
        out.println("</script>");

      } else {
        request.getSession().setAttribute("role", "admin");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Impossibile eliminare, Id null error!');");
        out.println("location='user/updateCar.jsp';");
        out.println("</script>");
      }
    }
  }
}
