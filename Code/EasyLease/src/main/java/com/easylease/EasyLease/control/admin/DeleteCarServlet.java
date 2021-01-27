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
import java.io.PrintWriter;

/**this servlet provides to delete a car from the database*/

@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
  static CarDAO CarDAO =DBCarDAO.getInstance();

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
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
      dispatcher.forward(request, response);
    } else if (role.equalsIgnoreCase("admin") == false) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
      dispatcher.forward(request, response);
    } else {

      String id = request.getParameter("ID_Delete");
      if (id != null && !id.equalsIgnoreCase("")) {
        Car car = CarDAO.retrieveById(id);
        car.setVisibility(false);
        CarDAO.update(car);

        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Auto eliminata con successo');");
        out.println("location='user/homePageJSP.jsp';");
        out.println("</script>");

      } else {
        request.getSession().setAttribute("role", "admin");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Impossibile eliminare, Id null error!');");
        out.println("location='user/updateCarJSP.jsp';");
        out.println("</script>");
      }
    }
  }
}
