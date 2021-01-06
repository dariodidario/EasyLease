package com.easylease.EasyLease.control.admin;


import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.user.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**this servlet provides to update a car already in the database*/

@WebServlet("/UpdateCarServlet")
public class UpdateCarServlet extends HttpServlet {
  static CarDAO CarDAO =DBCarDAO.getInstance();
  protected void doPost(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
  }

  protected void doGet(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("ID_Update");
    if (id != null && !id.equalsIgnoreCase("")) {
      Car car = CarDAO.retrieveById(id);
      String brand = request.getParameter("brand_Update");
      /**case update car brand*/
      if (brand != null && !brand.equalsIgnoreCase("")) {
        if (car.getBrand().equalsIgnoreCase(brand) == false) {
          car.setBrand(brand);
        }
      }
      String model = request.getParameter("model_Update");
      /**case update car model*/
      if (model != null && !model.equalsIgnoreCase("")) {
        if (car.getModel().equalsIgnoreCase(model) == false) {
          car.setModel(model);
        }
      }
      String img_car = request.getParameter("img_car_Update");
      /**case update car image*/
      if (img_car != null && !img_car.equalsIgnoreCase("")) {

        if (car.getImage().equalsIgnoreCase(img_car)==false) {
          String img_path = uploadImage(img_car, brand, model);
          car.setImage(img_path);
        }
      }
      String car_type = request.getParameter("car_type_Update");
      /**case update car type*/
      if (car_type != null && !car_type.equalsIgnoreCase("")) {
        if (car.getType().equalsIgnoreCase(car_type) == false) {
          car.setType(car_type);
        }
      }
      String doorsCar = request.getParameter("doors_Update");
      /**case update car doors*/
      if (doorsCar != null && !doorsCar.equalsIgnoreCase("")) {
        int doors = Integer.parseInt(doorsCar);
        if (car.getDoors() != doors) {
          car.setDoors(doors);
        }
      }
      String transmission = request.getParameter("transmission_Update");
      /**case update car transmission*/
      if (transmission != null && transmission.equalsIgnoreCase("")) {
        if (car.getTransmission().equalsIgnoreCase(transmission) == false) {
          car.setTransmission(transmission);
        }
      }
      String avg_consumptionCar = request.getParameter("avg_consumption_Update");
      /**case update car average consumption*/
      if (avg_consumptionCar != null && avg_consumptionCar.equalsIgnoreCase("")) {
        float avg_consumption = Float.parseFloat(avg_consumptionCar);
        if (car.getAvg_consumption() != avg_consumption) {
          car.setAvg_consumption(avg_consumption);
        }
      }
      String horse_powerCar = request.getParameter("horse_power_Update");
      /**case update car horse power*/
      if (horse_powerCar != null && !horse_powerCar.equalsIgnoreCase("")) {
        int horse_power = Integer.parseInt(horse_powerCar);
        if (car.getHorse_power() != horse_power) {
          car.setHorse_power(horse_power);
        }
      }
      String emission_class = request.getParameter("emission_class_Update");
      /**case update car emission class*/
      if (emission_class != null && !emission_class.equalsIgnoreCase("")) {
        if (car.getEmission_class().equalsIgnoreCase(emission_class) == false) {
          car.setEmission_class(emission_class);
        }
      }
      String co2_emissionsCar = request.getParameter("co2_emissions_Update");
      /**case update car co2 emissions*/
      if (co2_emissionsCar != null && !co2_emissionsCar.equalsIgnoreCase("")) {
        int co2_emissions = Integer.parseInt(co2_emissionsCar);
        if (car.getCo2_emissions() != co2_emissions) {
          car.setCo2_emissions(co2_emissions);
        }
      }
      String power_supply = request.getParameter("power_supply_Update");
      /**case update car power supply*/
      if (power_supply != null && !power_supply.equalsIgnoreCase("")) {
        if (car.getPowerSupply().equalsIgnoreCase(power_supply) == false) {
          car.setPowerSupply(power_supply);
        }
      }
      String capacityCar = request.getParameter("capacity_Update");
      /**case update car capacity*/
      if (capacityCar != null && !capacityCar.equalsIgnoreCase("")) {
        int capacity = Integer.parseInt(capacityCar);
        if (car.getCapacity() != capacity) {
          car.setCapacity(capacity);
        }
      }
      String priceCar = request.getParameter("price_Update");
      /**case update car price*/
      if (priceCar != null && !priceCar.equalsIgnoreCase("")) {
        float price = Float.parseFloat(priceCar);
        if (car.getPrice() != price) {
          car.setPrice(price);
        }
      }

      CarDAO.update(car);

      User user = (User) request.getSession().getAttribute("user");
      request.getSession().setAttribute("user", user);
      request.getSession().setAttribute("role", "advisor");

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('Auto aggiornata con successo');");
      out.println("location='user/homePageJSP.jsp';");
      out.println("</script>");

    }else{
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('Impossibile update, Id null error!');");
      out.println("location='user/updateCarJSP.jsp';");
      out.println("</script>");
    }
  }

  /** this method upload the image passed as parameters with the name of brand_model.jpg in the carImage's folder
   * @param  brand the brand of the car
   * @param model  the model of the car
   * @param image the image of the car
   * @return the image path*/
  private String uploadImage(String image, String brand, String model) throws IOException {
    File dir=new File("C:\\Users\\39392\\Desktop\\EasyLease\\src\\main\\webapp\\carImage");
    Path source =Path.of(image);
    Files.createFile(source);
    Files.setAttribute(source, "dos:readonly", true);
    Files.setAttribute(source, "dos:archive", true);
    String b=brand.replaceAll(" ","");
    String m=model.replaceAll(" ","");
    String img_path=b.toLowerCase()+"_"+m.toLowerCase()+".jpg";
    Path destination= Paths.get(dir.getPath()+"\\"+img_path);
    Files.move(source,destination);
    return  img_path;
  }
}
