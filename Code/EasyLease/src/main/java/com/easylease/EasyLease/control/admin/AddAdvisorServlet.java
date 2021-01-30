package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDao;
import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.easylease.EasyLease.model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

/**this servlet provides to add a new advisor into the database*/

@WebServlet("/AddAdvisorServlet")
public class AddAdvisorServlet extends HttpServlet {

  protected void doPost(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
  }

  protected void doGet(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    AdvisorDao advisorDAO = DbAdvisorDao.getInstance();
    String role = (String) request.getSession().getAttribute("role");
    if (role == null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
      dispatcher.forward(request, response);
    } else if (role.equalsIgnoreCase("admin") == false) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
      dispatcher.forward(request, response);
    } else {

      //check if all the jsp parameters are correct
      if (Boolean.valueOf(request.getParameter("email_valid")) == false) {//case invalid email
        request.getSession().setAttribute("error", "l'email non è valida");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("date_valid")) == false) {//case invalid date
        request.getSession().setAttribute("error", "la data non può essere superiore ad oggi");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("password_valid")) == false) {//case invalid password
        request.getSession().setAttribute("error", "la password non è valida");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else if (Boolean.valueOf(request.getParameter("confirm_valid")) == false) {//case invalid password confirm
        request.getSession().setAttribute("error", "le password non corrispondono");
        request.getSession().setAttribute("role", "admin");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
        dispatcher.forward(request, response);
      } else {//case correct parameters


        //follow the parameters recovered by jsp
        String advisor_name = request.getParameter("advisor_name");
        String advisor_surname = request.getParameter("advisor_surname");
        String advisor_email = request.getParameter("advisor_email");
        Date date = Date.valueOf(request.getParameter("advisor_date"));
        java.util.Date hireDate = date;
        String advisor_password = request.getParameter("advisor_password");

        //check if the advisor is already into the database
        boolean Advisor_ok = checkAdvisor(advisor_name, advisor_surname, advisor_email, hireDate);

        if (Advisor_ok == false) {//case is already present
          request.getSession().setAttribute("error", "Consulente già esistente");
          request.getSession().setAttribute("role", "admin");
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/addAdvisor.jsp");
          dispatcher.forward(request, response);
        } else {//case isn't already present

          //created the advisor id and checking if is it already into the database
          String advisor_id = checkID();

          Advisor advisor = new Advisor(advisor_id, advisor_name, advisor_surname, advisor_email, hireDate);

          advisorDAO.insert(advisor, advisor_password);

          //defined the session parameters
          User user = (User) request.getSession().getAttribute("user");

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

  /**this method created the id of advisor and check if the id is already in database
   * @return the new id of advisor*/
  private String checkID(){
    AdvisorDao advisorDAO = DbAdvisorDao.getInstance();
    List<Advisor> advisors=advisorDAO.retrieveAll();
    String idGenerate= "AD"+ IdGenerator.randomIdGenerator();
    if(advisors!=null) {
      for (int i = 0; i < advisors.size(); i++) {
        if (advisors.get(i).getId_user().equalsIgnoreCase(idGenerate) == true) {
          idGenerate = "AD" + IdGenerator.randomIdGenerator();
        }
      }
    }
    return idGenerate;
  }

  /**this method check if an advisor with these specified parameters is already in database
   * @return false if there's an other advisor with the specified parameters*/
  private boolean checkAdvisor(String advisor_name, String advisor_surname,
                               String advisor_email, java.util.Date hireDate){
    AdvisorDao advisorDAO = DbAdvisorDao.getInstance();

    List<Advisor> advisors = advisorDAO.retrieveAll();
    boolean Advisor_ok = true;
    if(advisors!=null) {
      for (int i = 0; i < advisors.size(); i++) {
        Advisor a = advisors.get(i);
        if (a.getFirst_name().equalsIgnoreCase(advisor_name) && a.getSurname().equalsIgnoreCase(advisor_surname)
                && a.getEmail().equalsIgnoreCase(advisor_email) && a.getHire_date().compareTo(hireDate) == 0)
        {
          Advisor_ok = false;
        }
      }
    }
    return Advisor_ok;
  }
}
