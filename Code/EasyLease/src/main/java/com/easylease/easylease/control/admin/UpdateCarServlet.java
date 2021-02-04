package com.easylease.easylease.control.admin;


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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/**
 * this servlet provides to update a car already in the database.
 */

@WebServlet("/UpdateCarServlet")
@MultipartConfig
public class UpdateCarServlet extends HttpServlet {

  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    CarDao CarDao = DbCarDao.getInstance();
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

      String id = request.getParameter("ID_Update");
      if (id != null && !id.equalsIgnoreCase("")) {
        Car car = CarDao.retrieveById(id);
        String brand = request.getParameter("brand_Update");
        /*case update car brand.*/
        if (brand != null && !brand.equalsIgnoreCase("")) {
          if (car.getBrand().equalsIgnoreCase(brand) == false) {
            car.setBrand(brand);
          }
        }
        String model = request.getParameter("model_Update");
        /*case update car model.*/
        if (model != null && !model.equalsIgnoreCase("")) {
          if (car.getModel().equalsIgnoreCase(model) == false) {
            car.setModel(model);
          }
        }

        if (request.getPart("img_car_Update") != null) {
          Part filePart = request.getPart("img_car_Update");
          /*case update car image.*/
          if (filePart.getSubmittedFileName() != null) {
            String imgPath = uploadImage(request, brand, model,
                car.getImage());
            car.setImage(imgPath);
          }
        }
        String carType = request.getParameter("car_type_Update");
        /*case update car type.*/
        if (carType != null && !carType.equalsIgnoreCase("")) {
          if (car.getType().equalsIgnoreCase(carType) == false) {
            car.setType(carType);
          }
        }
        String doorsCar = request.getParameter("doors_Update");
        /*case update car doors.*/
        if (doorsCar != null && !doorsCar.equalsIgnoreCase("")) {
          int doors = Integer.parseInt(doorsCar);
          if (car.getDoors() != doors) {
            car.setDoors(doors);
          }
        }
        String transmission = request.getParameter("transmission_Update");
        /*case update car transmission.*/
        if (transmission != null && !transmission.equalsIgnoreCase("")) {
          if (car.getTransmission().equalsIgnoreCase(transmission) == false) {
            car.setTransmission(transmission);
          }
        }
        String avgConsumptionCar = request.getParameter(
            "avg_consumption_Update");
        /*case update car average consumption*/
        if (avgConsumptionCar != null
            && !avgConsumptionCar.equalsIgnoreCase("")) {
          float avgConsumption = Float.parseFloat(avgConsumptionCar);
          if (car.getAvgConsumption() != avgConsumption) {
            car.setAvgConsumption(avgConsumption);
          }
        }
        String horsePowerCar = request.getParameter("horse_power_Update");
        /*case update car horse power.*/
        if (horsePowerCar != null && !horsePowerCar.equalsIgnoreCase("")) {
          int horsePower = Integer.parseInt(horsePowerCar);
          if (car.getHorsePower() != horsePower) {
            car.setHorsePower(horsePower);
          }
        }
        String emissionClass = request.getParameter("emission_class_Update");
        /*case update car emission class.*/
        if (emissionClass != null && !emissionClass.equalsIgnoreCase("")) {
          if (car.getEmissionClass().equalsIgnoreCase(emissionClass)
              == false) {
            car.setEmissionClass(emissionClass);
          }
        }
        String co2EmissionsCar = request.getParameter("co2_emissions_Update");
        /*case update car co2 emissions.*/
        if (co2EmissionsCar != null
            && !co2EmissionsCar.equalsIgnoreCase("")) {
          int co2Emissions = Integer.parseInt(co2EmissionsCar);
          if (car.getCo2Emissions() != co2Emissions) {
            car.setCo2Emissions(co2Emissions);
          }
        }
        String powerSupply = request.getParameter("power_supply_Update");
        /*case update car power supply.*/
        if (powerSupply != null && !powerSupply.equalsIgnoreCase("")) {
          if (car.getPowerSupply().equalsIgnoreCase(powerSupply) == false) {
            car.setPowerSupply(powerSupply);
          }
        }
        String capacityCar = request.getParameter("capacity_Update");
        /*case update car capacity.*/
        if (capacityCar != null && !capacityCar.equalsIgnoreCase("")) {
          int capacity = Integer.parseInt(capacityCar);
          if (car.getCapacity() != capacity) {
            car.setCapacity(capacity);
          }
        }
        String priceCar = request.getParameter("price_Update");
        /*case update car price.*/
        if (priceCar != null && !priceCar.equalsIgnoreCase("")) {
          float price = Float.parseFloat(priceCar);
          if (car.getPrice() != price) {
            car.setPrice(price);
          }
        }

        CarDao.update(car);

        Admin user = (Admin) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", "admin");
        request.getSession().setAttribute("carList", null);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Auto aggiornata con successo');");
        out.println("location='user/homePage.jsp';");
        out.println("</script>");

      } else {
        request.getSession().setAttribute("role", "admin");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Impossibile update, Id null error!');");
        out.println("location='user/updateCar.jsp';");
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
   * this method upload the image passed as parameters of the request
   * with the name of brand_model.jpg in the carImage's folder.
   *
   * @param brand   the brand of the car
   * @param model   the model of the car
   * @param request the image of the car
   * @return the image path
   */
  private String uploadImage(
      HttpServletRequest request, String brand, String model,
      String imgOld) throws IOException, ServletException {
    File canc = new File(request.getServletContext().getRealPath("img"));
    File rem = new File(canc, imgOld);
    Files.delete(rem.toPath());

    Part filePart = request.getPart(
        "img_car_Update"); // Retrieves <input type="file" name="file">
    String fileName = Paths.get(filePart.getSubmittedFileName())
        .getFileName()
        .toString(); // MSIE fix.
    InputStream fileContent = filePart.getInputStream();

    String b = brand.replaceAll(" ", "");
    String m = model.replaceAll(" ", "");
    String imgPath = b.toLowerCase() + "_" + m.toLowerCase() + ".jpg";

    File uploads = new File(request.getServletContext().getRealPath("img"));
    File file = new File(uploads, imgPath);

    Files.copy(fileContent, file.toPath());

    return imgPath;
  }
}
