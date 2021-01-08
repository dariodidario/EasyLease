package com.easylease.EasyLease.control.admin;


import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**this servlet provides to add a new car into the database*/

@WebServlet("/AddCarServlet")
@MultipartConfig
public class AddCarServlet extends HttpServlet {
  static CarDAO CarDAO =DBCarDAO.getInstance();
  protected void doPost(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
      String role =(String) request.getSession().getAttribute("role");
      if (role == null) {
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
          dispatcher.forward(request, response);
      } else if (role.equalsIgnoreCase("admin") == false) {
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fragments/error403.jsp");
          dispatcher.forward(request, response);
      } else {

          //follow the parameters recovered by jsp
          String brand = request.getParameter("brand");
          String model = request.getParameter("model");
          String b=brand.replaceAll(" ","");
          String m=model.replaceAll(" ","");
          String img_car =b.toLowerCase()+"_"+m.toLowerCase()+".jpg";
          String car_type = request.getParameter("car_type");
          int doors = Integer.parseInt(request.getParameter("doors"));
          String transmission = request.getParameter("transmission");
          float avg_consumption = Float.parseFloat(request.getParameter("avg_consumption"));
          int horse_power = Integer.parseInt(request.getParameter("horse_power"));
          String emission_class = request.getParameter("emission_class");
          int co2_emissions = Integer.parseInt(request.getParameter("co2_emissions"));
          String power_supply = request.getParameter("power_supply");
          int capacity = Integer.parseInt(request.getParameter("capacity"));
          float price = Float.parseFloat(request.getParameter("price"));

          //check if the car is already into the database
          boolean Car_ok = checkCar(brand, model, img_car, car_type, doors, transmission, avg_consumption,
                  horse_power, emission_class, co2_emissions, power_supply, capacity, price);

          if (Car_ok == false) {//case if is already present
              request.getSession().setAttribute("role", "admin");
              response.setContentType("text/html;charset=UTF-8");

              PrintWriter out = response.getWriter();
              out.println("alert('Auto già esistente');");
              out.println("location='admin/addCarJSP.jsp';");
              out.println("</script>");
          } else {//case if isn't already present

              // create the car id and check if is it already into the database
              String id = checkID();

              //upload the image into the project directory carImage
              String img_path = uploadImage(request, brand, model);


              Car car = new Car(id, brand, model, price, car_type, true, doors, transmission, avg_consumption, horse_power, emission_class, co2_emissions, power_supply, capacity, img_path);

              CarDAO.insert(car);




              //defined the session parameters
              User user = (User) request.getSession().getAttribute("user");
              request.getSession().setAttribute("user", user);
              request.getSession().setAttribute("role", "admin");
              Car car1 = CarDAO.retrieveById(id);

              //return statement
              response.setContentType("text/html;charset=UTF-8");
              PrintWriter out = response.getWriter();
              out.println("<script type=\"text/javascript\">");
              out.println("alert('Auto aggiunta con successo');");
              out.println("location='user/homePageJSP.jsp';");
              out.println("</script>");
          }
      }
  }

  protected void doGet(
          HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
     doPost(request,response);
  }

  /** this method upload the image passed as parameters of the request with the name of brand_model.jpg in the carImage's folder
   * @param  brand the brand of the car
   * @param model  the model of the car
   * @param request the image of the car
   * @return the image path*/
    private String uploadImage(HttpServletRequest request, String brand, String model) throws IOException, ServletException {
        Part filePart = request.getPart("image_path"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        String b=brand.replaceAll(" ","");
        String m=model.replaceAll(" ","");
        String img_path=b.toLowerCase()+"_"+m.toLowerCase()+".jpg";

        File uploads = new File(request.getServletContext().getRealPath("img"));
        File file = new File(uploads, img_path);


        Files.copy(fileContent, file.toPath());


        return  img_path;
    }

    /**this method created the id of car and check if the id is present in database
     * @return the new id of car*/
    private String checkID(){
        List<Car> cars= CarDAO.retrieveAll();
        String idGenerate= "CA"+ IdGenerator.randomIdGenerator();
        if(cars!=null) {
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getId().equalsIgnoreCase(idGenerate) == true) {
                    idGenerate = "CA" + IdGenerator.randomIdGenerator();
                }
            }
        }
        return idGenerate;
    }

    /**this method check if a Car with these specified parameters is already in database
     * @return false if there's an other car with the specified parameters*/
    private boolean checkCar(String brand, String model, String img_car,
                             String car_type, int doors, String trasmission, float avg_consumption,
                             int horse_power, String emission_class, int co2_emissions,
                             String power_supply, int capacity, float price){

        List<Car> cars= CarDAO.retrieveAll();
        boolean Car_ok=true;
        if(cars!=null) {
            for (int i = 0; i < cars.size(); i++) {
                Car c = cars.get(i);
                if (c.getBrand().equalsIgnoreCase(brand) && c.getModel().equalsIgnoreCase(model)
                        && c.getImage().equalsIgnoreCase(img_car) && c.getType().equalsIgnoreCase(car_type)
                        && c.getDoors() == doors && c.getTransmission().equalsIgnoreCase(trasmission) && c.getAvg_consumption() == avg_consumption
                        && c.getHorse_power() == horse_power && c.getEmission_class().equalsIgnoreCase(emission_class) && c.getCo2_emissions() == co2_emissions
                        && c.getPowerSupply().equalsIgnoreCase(power_supply) && c.getCapacity() == capacity && c.getPrice() == price) {
                    Car_ok = false;
                }
            }
        }
      return  Car_ok;
    }

}
