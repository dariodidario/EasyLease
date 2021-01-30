package com.easylease.EasyLease.model.car;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DbCarDaoTest {
    private static CarDao dao;
    private static DbConnection dbConnection;
    @BeforeEach
    void setUP() throws SQLException {
        dbConnection = DbConnection.getInstance();
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/easylease");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("2935Michele");
        mysqlDataSource.setServerTimezone("UTC");
        mysqlDataSource.setVerifyServerCertificate(false);
        mysqlDataSource.setUseSSL(false);

        dbConnection.setDataSource(mysqlDataSource);
        dao= DbCarDao.getInstance();
    }


    /**test with correct id parameter expected car in return**/
    @Test
    void retriveById_test1() {
           Car car=dao.retrieveById("CA0T753");
           assertEquals("CA0T753",car.getId_car());
    }

    /**test with nonexistent id parameter expected null return**/
    @Test
    void retriveById_test2() {
        Car car=dao.retrieveById("jsjsj");
        assertNull(car);
    }

    /**test with null id parameter expected exception**/
    @Test
    void retriveById_test3() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveById(null);});
    }

    /**test with empty id parameter expected exception **/
    @Test
    void retriveById_test4() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveById("");});
    }

    /**test with correct model parameter expected car in return**/
    @Test
    void retriveByModel_test1() {
        Car car=dao.retrieveByModel("Corsa");
        assertEquals("Corsa",car.getModel());
    }

    /**test with nonexistent model parameter expected null return**/
    @Test
    void retriveByModel_test2() {
        assertNull(dao.retrieveByModel("0"));
    }

    /**test with null model parameter expected exception**/
    @Test
    void retriveByModel_test3() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByModel(null);});
    }

    /**test with empty model parameter expected exception**/
    @Test
    void retriveByModel_test4() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByModel("");});
    }

    /**test with correct brand parameter expected list of car**/
    @Test
    void retriveByBrand_test1() {
        List<Car> cars=dao.retrieveByBrand("Fiat");
        for(int i=0;i<cars.size();i++){
            assertEquals("Fiat",cars.get(i).getBrand());
        }
    }

    /**test with nonexistent brand parameter expected empty list**/
    @Test
    void retriveByBrand_test2() {
        List<Car> cars=dao.retrieveByBrand("Dario di Dario");
        assertEquals(0,cars.size());
    }

    /**test with null brand parameter expected exception**/
    @Test
    void retriveByBrand_test3() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByBrand(null);});
    }

    /**test with empty brand parameter expected exception**/
    @Test
    void retriveByBrand_test4() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByBrand("");});
    }

    /**test with correct type parameter expected list of car**/
    @Test
    void retriveByType_test1() {
        List<Car> cars=dao.retrieveByType("Sport");
        for(int i=0;i<cars.size();i++){
            assertEquals("Sport",cars.get(i).getType());
        }
    }

    /**test with nonexistent type parameter expected empty list**/
    @Test
    void retriveByType_test2() {
        List<Car> cars=dao.retrieveByType("Amodomio");
        assertEquals(0,cars.size());
    }

    /**test with null type parameter expected exception**/
    @Test
    void retriveByType_test3() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByType(null);});
    }

    /**test with empty type parameter expected exception**/
    @Test
    void retriveByType_test4() {
        assertThrows(IllegalArgumentException.class,()->{dao.retrieveByType("");});
    }

    /**test correct expected list of all car**/
    @Test
    void retriveAll_test1() {
        List<Car> cars=dao.retrieveAll();
        assertNotNull(cars);
    }

    /**test with correct car parameter expected success**/
    @Test
    void update_test1() {
        Car car=dao.retrieveById("CA6qSDe");
        car.setDoors(5);
        dao.update(car);
        assertEquals(5,dao.retrieveById("CA6qSDe").getDoors());

        car.setDoors(3);
        dao.update(car);
    }

    /**test with null car parameter expected exception**/
    @Test
    void update_test2() {
        assertThrows(IllegalArgumentException.class,()->{dao.update(null);});
    }

    /**test with correct car parameter expected success**/
    @Test
    void delete_test1() {
      Car car=dao.retrieveByModel("GR Supra");
      dao.delete(car);
      assertNull(dao.retrieveByModel("GR Supra"));

      dao.insert(car);
    }

    /**test with null car parameter expected exception**/
    @Test
    void delete_test2() {
        assertThrows(IllegalArgumentException.class,()->{dao.delete(null);});
    }

    /**test with correct car parameter expected success**/
    @Test
    void insert_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        dao.insert(car);
        assertNotNull(dao.retrieveById("CAAA111"));

        dao.delete(car);
    }

    /**test with null car parameter expected exception**/
    @Test
    void insert_test2() {
        assertThrows(IllegalArgumentException.class,()->{dao.insert(null);});
    }
}
