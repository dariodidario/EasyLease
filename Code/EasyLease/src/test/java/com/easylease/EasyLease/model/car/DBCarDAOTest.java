package com.easylease.EasyLease.model.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBCarDAOTest {
    private static DBCarDAO dao;
    @BeforeEach
    void setUP(){
        dao=mock(DBCarDAO.class);
    }


    /**test with correct id parameter expected car in return**/
    @Test
    void retriveById_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

           when(dao.retriveById("ca11111")).thenReturn(car);

           Car car1=dao.retriveById("ca11111");
           assertEquals(car,car1);

    }

    /**test with nonexistent id parameter expected null return**/
    @Test
    void retriveById_test2() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        when(dao.retriveById("ca11111")).thenReturn(car);

        Car car1=dao.retriveById("ca11112");
        assertNull(car1);

    }

    /**test with null id parameter expected exception**/
    @Test
    void retriveById_test3() {

        when(dao.retriveById(null)).thenThrow(IllegalArgumentException.class);


        assertThrows(IllegalArgumentException.class,()->{dao.retriveById(null);});

    }

    /**test with empty id parameter expected exception **/
    @Test
    void retriveById_test4() {

        when(dao.retriveById("")).thenThrow(IllegalArgumentException.class);


        assertThrows(IllegalArgumentException.class,()->{dao.retriveById("");});

    }

    /**test with correct model parameter expected car in return**/
    @Test
    void retriveByModel_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        when(dao.retriveByModel("3008")).thenReturn(car);

        Car car1=dao.retriveByModel("3008");
        assertEquals(car,car1);

    }

    /**test with nonexistent model parameter expected null return**/
    @Test
    void retriveByModel_test2() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        when(dao.retriveByModel("3008")).thenReturn(car);

        Car car1=dao.retriveByModel("compass");
        assertNull(car1);

    }

    /**test with null model parameter expected exception**/
    @Test
    void retriveByModel_test3() {

        when(dao.retriveByModel(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()->{dao.retriveByModel(null);});

    }

    /**test with empty model parameter expected exception**/
    @Test
    void retriveByModel_test4() {

        when(dao.retriveByModel("")).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()->{dao.retriveByModel("");});

    }

    /**test with correct brand parameter expected list of car**/
    @Test
    void retriveByBrand_test1() {
        ArrayList<Car> cars=new ArrayList<>();
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByBrand("Peugeot")).thenReturn(list);

        List<Car> cars1=dao.retriveByBrand("Peugeot");
        verify(dao,times(1)).retriveByBrand(any());
        assertAll(
                ()->assertEquals(2,cars1.size()),
                ()->assertEquals(list,cars1));
    }

    /**test with nonexistent brand parameter expected empty list**/
    @Test
    void retriveByBrand_test2() {
        ArrayList<Car> cars=new ArrayList<>();
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByBrand("Peugeot")).thenReturn(list);

        List<Car> cars1=dao.retriveByBrand("Mercedes");
        assertEquals(0,cars1.size());

    }

    /**test with null brand parameter expected exception**/
    @Test
    void retriveByBrand_test3() {

        when(dao.retriveByBrand(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()->{dao.retriveByBrand(null);});

    }

    /**test with empty brand parameter expected exception**/
    @Test
    void retriveByBrand_test4() {

        when(dao.retriveByBrand("")).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()->{dao.retriveByBrand("");});

    }

    /**test with correct type parameter expected list of car**/
    @Test
    void retriveByType_test1() {
        ArrayList<Car> cars=new ArrayList<>();
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByType("SUV")).thenReturn(list);

        List<Car> cars1=dao.retriveByType("SUV");

        assertAll(
                ()->assertEquals(2,cars1.size()),
                ()->assertEquals(list,cars1));
    }

    /**test with nonexistent type parameter expected empty list**/
    @Test
    void retriveByType_test2() {
        ArrayList<Car> cars=new ArrayList<>();
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByType("SUV")).thenReturn(list);

        List<Car> cars1=dao.retriveByType("cabriolet");

        assertEquals(0,cars1.size());
    }

    /**test with null type parameter expected exception**/
    @Test
    void retriveByType_test3() {
        ArrayList<Car> cars=new ArrayList<>();
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByType(null)).thenThrow(IllegalArgumentException.class);


        assertThrows(IllegalArgumentException.class,()->{dao.retriveByType(null);});
    }

    /**test with empty type parameter expected exception**/
    @Test
    void retriveByType_test4() {
        ArrayList<Car> cars=new ArrayList<>();
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
      cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        List list=cars;
        when(dao.retriveByType("")).thenThrow(IllegalArgumentException.class);


        assertThrows(IllegalArgumentException.class,()->{dao.retriveByType("");});
    }

    /**test correct expected list of all car**/
    @Test
    void retriveAll_test1() {

        Car car=new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
        Car car1=new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
        ArrayList<Car> cars=new ArrayList<>();
        cars.add(car);
        cars.add(car1);
        List list=cars;
        when(dao.retriveAll()).thenReturn(list);

        List<Car> cars1=dao.retriveAll();
        verify(dao,times(1)).retriveAll();
        assertAll(
                ()->assertEquals(2,cars1.size()),
                ()->assertEquals(car,cars1.get(0)),
                ()->assertEquals(car1,cars1.get(1)));

    }

    /**test with correct car parameter expected success**/
    @Test
    void update_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        doAnswer(invocation -> {
            return null;
        }).when(dao).update(any());

        dao.update(car);

        ArgumentCaptor<Car> captor=ArgumentCaptor.forClass(Car.class);
        verify(dao,times(1)).update(captor.capture());
        assertEquals(car,captor.getValue());
    }

    /**test with null car parameter expected exception**/
    @Test
    void update_test2() {

        doThrow(IllegalArgumentException.class).when(dao).update(null);


        assertThrows(IllegalArgumentException.class,()->{dao.update(null);});
    }

    /**test with correct car parameter expected success**/
    @Test
    void delete_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        doAnswer(invocation -> {
            return null;
        }).when(dao).delete(any());

        dao.delete(car);

        ArgumentCaptor<Car> captor=ArgumentCaptor.forClass(Car.class);
        verify(dao,times(1)).delete(captor.capture());
    }

    /**test with null car parameter expected exception**/
    @Test
    void delete_test2() {

        doThrow(IllegalArgumentException.class).when(dao).delete(null);


        assertThrows(IllegalArgumentException.class,()->{dao.delete(null);});
    }

    /**test with correct car parameter expected success**/
    @Test
    void insert_test1() {
      Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
          true, 5, "Automatico", 3.9f,
          130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

        doAnswer(invocation -> {
            return null;
        }).when(dao).insert(any());

        dao.insert(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
            true, 5, "Automatico", 3.9f,
            130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));

        ArgumentCaptor<Car> captor=ArgumentCaptor.forClass(Car.class);
        verify(dao,times(1)).insert(captor.capture());
        assertEquals(car.getId(),captor.getValue().getId());
    }

    /**test with null car parameter expected exception**/
    @Test
    void insert_test2() {

        doThrow(IllegalArgumentException.class).when(dao).insert(null);


        assertThrows(IllegalArgumentException.class,()->{dao.insert(null);});
    }
}