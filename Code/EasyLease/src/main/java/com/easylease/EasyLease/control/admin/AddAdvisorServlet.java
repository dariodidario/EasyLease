package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.admin.Admin;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * this servlet provides to add a new advisor into the database.
 */

@WebServlet("/AddAdvisorServlet")
public class AddAdvisorServlet extends HttpServlet {

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    AdvisorDAO advisorDao = DBAdvisorDAO.getInstance();
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

      //check if all the jsp parameters are correct
      if (Boolean.valueOf(request.getParameter("email_valid"))
          == false) { //case invalid email
        request.getSession().setAttribute("error", "l'email non è valida");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("date_valid"))
          == false) { //case invalid date
        request.getSession()
            .setAttribute("error", "la data non può essere superiore ad oggi");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("password_valid"))
          == false) { //case invalid password
        request.getSession().setAttribute("error", "la password non è valida");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("confirm_valid"))
          == false) { //case invalid password confirm
        request.getSession()
            .setAttribute("error", "le password non corrispondono");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            "/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else { //case correct parameters

        //follow the parameters recovered by jsp
        String advisorName = request.getParameter("advisor_name");
        String advisorSurname = request.getParameter("advisor_surname");
        String advisorEmail = request.getParameter("advisor_email");
        Date date = Date.valueOf(request.getParameter("advisor_date"));
        java.util.Date hireDate = date;
        String advisorPassword = request.getParameter("advisor_password");

        //check if the advisor is already into the database
        boolean advisorOk = checkAdvisor(advisorName, advisorSurname,
            advisorEmail, hireDate);

        if (advisorOk == false) { //case is already present
          request.getSession()
              .setAttribute("error", "Consulente già esistente");
          request.getSession().setAttribute("role", "admin");
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
              "/admin/addAdvisor.jsp");
          dispatcher.forward(request, response);
        } else { //case isn't already present

          //created the advisor id and checking if is it already into the database
          String advisorId = checkId();

          Advisor advisor = new Advisor(advisorId, advisorName,
              advisorSurname, advisorEmail, hireDate);

          advisorDao.insert(advisor, advisorPassword);

          //defined the session parameters
          Admin user = (Admin) request.getSession().getAttribute("user");

          request.getSession().setAttribute("user", user);
          request.getSession().setAttribute("role", "admin");
          request.getSession().setAttribute("error", "");

          //return statement
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<script type=\"text/javascript\">");
          out.println("alert('Consulente aggiunto con successo');");
          out.println("location='user/homePage.jsp';");
          out.println("</script>");
        }
      }
    }
  }

  /**
   * this method created the id of advisor and check if the id is already in database.
   *
   * @return the new id of advisor
   */
  private String checkId() {
    AdvisorDAO advisorDao = DBAdvisorDAO.getInstance();
    List<Advisor> advisors = advisorDao.retrieveAll();
    String idGenerate = "AD" + IdGenerator.randomIdGenerator();
    if (advisors != null) {
      for (int i = 0; i < advisors.size(); i++) {
        if (advisors.get(i).getIdUser().equalsIgnoreCase(idGenerate) == true) {
          idGenerate = "AD" + IdGenerator.randomIdGenerator();
        }
      }
    }
    return idGenerate;
  }

  /**
   * this method check if an advisor with these specified parameters is already in database.
   *
   * @return false if there's an other advisor with the specified parameters
   */
  private boolean checkAdvisor(
      String advisorName, String advisorSurname,
      String advisorEmail, java.util.Date hireDate) {
    AdvisorDAO advisorDao = DBAdvisorDAO.getInstance();

    List<Advisor> advisors = advisorDao.retrieveAll();
    boolean advisorOk = true;
    if (advisors != null) {
      for (int i = 0; i < advisors.size(); i++) {
        Advisor a = advisors.get(i);
        if (a.getFirstName().equalsIgnoreCase(advisorName)
            && a.getSurname().equalsIgnoreCase(advisorSurname)
            && a.getEmail().equalsIgnoreCase(advisorEmail)
            && a.getHireDate().compareTo(hireDate) == 0) {
          advisorOk = false;
        }
      }
    }
    return advisorOk;
  }
}
