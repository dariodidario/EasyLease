package com.easylease.easylease.control.admin;


import com.easylease.easylease.control.utility.IdGenerator;
import com.easylease.easylease.model.admin.Admin;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.car.CarDao;
import com.easylease.easylease.model.car.DbCarDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * this servlet provides to add a new car into the database.
 */

@WebServlet("/AddCarServlet")
@MultipartConfig
public class AddCarServlet extends HttpServlet {

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    CarDao carDao = DbCarDao.getInstance();
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

      //follow the parameters recovered by jsp
      String brand = "";
      if (request.getParameter("brand") != null) {
        brand = request.getParameter("brand");
      }
      String model = "";
      if (request.getParameter("model") != null) {
        model = request.getParameter("model");
      }
      String carType = "";
      if (request.getParameter("car_type") != null) {
        carType = request.getParameter("car_type");
      }
      int doors = 0;
      if (request.getParameter("doors") != null) {
        doors = Integer.parseInt(request.getParameter("doors"));
      }
      String transmission = "";
      if (request.getParameter("transmission") != null) {
        transmission = request.getParameter("transmission");
      }
      float avgConsumption = 0;
      if (request.getParameter("avg_consumption") != null) {
        avgConsumption = Float.parseFloat(
            request.getParameter("avg_consumption"));
      }
      int horsePower = 0;
      if (request.getParameter("horse_power") != null) {
        horsePower = Integer.parseInt(request.getParameter("horse_power"));
      }
      String emissionClass = "";
      if (request.getParameter("emission_class") != null) {
        emissionClass = request.getParameter("emission_class");
      }
      int co2Emissions = 0;
      if (request.getParameter("co2_emissions") != null) {
        co2Emissions = Integer.parseInt(request.getParameter("co2_emissions"));
      }
      String powerSupply = "";
      if (request.getParameter("power_supply") != null) {
        powerSupply = request.getParameter("power_supply");
      }
      int capacity = 0;
      if (request.getParameter("capacity") != null) {
        capacity = Integer.parseInt(request.getParameter("capacity"));
      }
      float price = 0;
      if (request.getParameter("price") != null) {
        price = Float.parseFloat(request.getParameter("price"));
      }
      String b = brand.replaceAll(" ", "");
      String m = model.replaceAll(" ", "");
      String imgCar = b.toLowerCase() + "_" + m.toLowerCase() + ".jpg";
      //check if the car is already into the database
      boolean carOk = checkCar(brand, model, imgCar, carType, doors,
          transmission, avgConsumption,
          horsePower, emissionClass, co2Emissions, powerSupply, capacity,
          price);

      if (carOk == false) { //case if is already present
        request.getSession().setAttribute("role", "admin");
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().setAttribute("carList", null);

        PrintWriter out = response.getWriter();
        out.println("alert('Auto già esistente');");
        out.println("location='admin/addCar.jsp';");
        out.println("</script>");
      } else { //case if isn't already present

        // create the car id and check if is it already into the database
        String id = checkId();

        //upload the image into the project directory carImage
        uploadImage(request, brand, model);

        Car car = new Car(id, brand, model, price, carType, true, doors,
            transmission, avgConsumption, horsePower, emissionClass,
            co2Emissions, powerSupply, capacity, imgCar);

        carDao.insert(car);

        //defined the session parameters
        Admin user = (Admin) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");
        Car car1 = carDao.retrieveById(id);

        //return statement
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Auto aggiunta con successo');");
        out.println("location='user/homePage.jsp';");
        out.println("</script>");
      }
    }
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * this method upload the image passed as parameters of the
   * request with the name of brand_model.jpg in the carImage's folder.
   *
   * @param brand   the brand of the car
   * @param model   the model of the car
   * @param request the image of the car
   * @return the image path
   */
  private String uploadImage(
      HttpServletRequest request, String brand,
      String model) throws IOException, ServletException {

    String imgPath = "";
    if (request.getPart("image_path") != null) {
      Part filePart = request.getPart(
          "image_path"); // Retrieves <input type="file" name="file">
      String fileName = Paths.get(filePart.getSubmittedFileName())
          .getFileName()
          .toString(); // MSIE fix.
      InputStream fileContent = filePart.getInputStream();

      String b = brand.replaceAll(" ", "");
      String m = model.replaceAll(" ", "");
      imgPath = b.toLowerCase() + "_" + m.toLowerCase() + ".jpg";

      File uploads = new File(request.getServletContext().getRealPath("img"));
      File file = new File(uploads, imgPath);

      Files.copy(fileContent, file.toPath());
    }

    return imgPath;
  }

  /**
   * this method created the id of car and check if the id is present in database.
   *
   * @return the new id of car
   */
  private String checkId() {
    CarDao carDao = DbCarDao.getInstance();
    List<Car> cars = carDao.retrieveAll();
    String idGenerate = "CA" + IdGenerator.randomIdGenerator();
    if (cars != null) {
      for (int i = 0; i < cars.size(); i++) {
        if (cars.get(i).getIdCar().equalsIgnoreCase(idGenerate) == true) {
          idGenerate = "CA" + IdGenerator.randomIdGenerator();
        }
      }
    }
    return idGenerate;
  }

  /**
   * this method check if a Car with these specified parameters is already in database.
   *
   * @return false if there's an other car with the specified parameters
   */
  private boolean checkCar(
      String brand, String model, String imgCar,
      String carType, int doors, String trasmission, float avgConsumption,
      int horsePower, String emissionClass, int co2Emissions,
      String powerSupply, int capacity, float price) {
    CarDao carDao = DbCarDao.getInstance();
    List<Car> cars = carDao.retrieveAll();
    boolean carOk = true;
    if (cars != null) {
      for (int i = 0; i < cars.size(); i++) {
        Car c = cars.get(i);
        if (c.getBrand().equalsIgnoreCase(brand)
            && c.getModel().equalsIgnoreCase(model)
            && c.getImage().equalsIgnoreCase(imgCar)
            && c.getType().equalsIgnoreCase(carType)
            && c.getDoors() == doors
            && c.getTransmission().equalsIgnoreCase(trasmission)
            && c.getAvgConsumption() == avgConsumption
            && c.getHorsePower() == horsePower
            && c.getEmissionClass().equalsIgnoreCase(emissionClass)
            && c.getCo2Emissions() == co2Emissions
            && c.getPowerSupply().equalsIgnoreCase(powerSupply)
            && c.getCapacity() == capacity && c.getPrice() == price) {
          carOk = false;
        }
      }
    }
    return carOk;
  }

}
