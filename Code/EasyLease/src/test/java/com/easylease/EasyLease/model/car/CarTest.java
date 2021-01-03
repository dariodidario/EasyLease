package com.easylease.EasyLease.model.car;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class CarTest {
    Car car=new Car("AAAA111", "Peugeot", "3008", 249, "SUV", "Diesel", "peugeot_3008.jpg", true, 5, "Automatico", 3.9f, 130, "Euro 6", 104, 1499);

    @Test
    void testCostructor(){
        Car c=new Car();
        assertNotNull(c);
    }

    @org.junit.jupiter.api.Test
    public void testGetId() {
        String s=car.getId();
        assertEquals("get id errato","AAAA111",s);
    }

    @org.junit.jupiter.api.Test
    void testGetBrand() {
        String s=car.getBrand();
        assertEquals("get brand errato","Peugeot",s);
    }

    @Test
    void test1_SetId() {
        car.setId("ca11112");
        String s=car.getId();
        assertEquals("set id errato","ca11112",s);
    }


    @Test
    void testSetBrand() {
        car.setBrand("Renault");
        String s=car.getBrand();
        assertEquals("set brand errato","Renault",s);
    }

    @Test
    void testGetModel() {
        String s=car.getModel();
        assertEquals("get model errato","3008",s);
    }

    @Test
    void testSetModel() {
        car.setModel("2008");
        String s=car.getModel();
        assertEquals("set model errato","2008",s);
    }

    @Test
    void testGetPrice() {
        float s=car.getPrice();
        assertEquals("get price errato",249,s,1);
    }

    @Test
    void testSetPrice() {
        car.setPrice(2409);
        float s=car.getPrice();
        assertEquals("set price errato",2409,s,1);
    }

    @Test
    void testGetType() {
        String s=car.getType();
        assertEquals("get type errato","SUV",s);
    }

    @Test
    void testSetType() {
        car.setType("berlina");
        String s=car.getType();
        assertEquals("set type errato","berlina",s);
    }

    @Test
    void testGetPowerSupply() {
        String s=car.getPowerSupply();
        assertEquals("get power_supply errato","Diesel",s);
    }

    @Test
    void testSetPowerSupply() {
        car.setPowerSupply("Benzina");
        String s=car.getPowerSupply();
        assertEquals("set power_supply errato","Benzina",s);
    }

    @Test
    void testGetVisibility() {
        boolean s=car.getVisibility();
        assertEquals("get visibility errato",true,s);
    }

    @Test
    void testSetVisibility() {
        car.setVisibility(false);
        boolean s=car.getVisibility();
        assertEquals("set visibility errato",false,s);
    }

    @Test
    void testGetDoors() {
        int s=car.getDoors();
        assertEquals("get doors errato",5,s);
    }

    @Test
    void testSetDoors() {
        car.setDoors(3);
        int s=car.getDoors();
        assertEquals("set doors errato",3,s);
    }

    @Test
    void testGetTrasmision() {
        String s=car.getTrasmision();
        assertEquals("get id errato","Automatico",s);
    }

    @Test
    void testSetTrasmision() {
        car.setTrasmision("Manuale");
        String s=car.getTrasmision();
        assertEquals("set id errato","Manuale",s);
    }

    @Test
    void testGetAvg_consumption() {
        float s=car.getAvg_consumption();
        assertEquals("get avg consumption errato",3.9f,s,1);
    }

    @Test
    void tetsSetAvg_consumption() {
        car.setAvg_consumption(2.9f);
        float s=car.getAvg_consumption();
        assertEquals("set avg consumption errato",2.9f,s,1);
    }

    @Test
    void testGetHorse_power() {
        int s=car.getHorse_power();
        assertEquals("get horse power errato",130,s);
    }

    @Test
    void testSetHorse_power() {
        car.setHorse_power(90);
        int s=car.getHorse_power();
        assertEquals("set horse power errato",90,s);
    }

    @Test
    void testGetEmission_class() {
        String s=car.getEmission_class();
        assertEquals("get emission class errato","Euro 6",s);
    }

    @Test
    void testSetEmission_class() {
        car.setEmission_class("Euro 5");
        String s=car.getEmission_class();
        assertEquals("set emission class errato","Euro 5",s);
    }

    @Test
    void tsetGetCo2_emissions() {
        int s=car.getCo2_emissions();
        assertEquals("get co2 emissions errato",104,s);
    }

    @Test
    void testSetCo2_emissions() {
        car.setCo2_emissions(100);
        int s=car.getCo2_emissions();
        assertEquals("set co2 emissions errato",100,s);
    }

    @Test
    void testGetCc() {
        int s=car.getCc();
        assertEquals("get cc errato",1499,s);
    }

    @Test
    void testSetCc() {
        car.setCc(1400);
        int s=car.getCc();
        assertEquals("set cc errato",1400,s);
    }

    @Test
    void testGetImage() {
        String s=car.getImage();
        assertEquals("get image errato","peugeot_3008.jpg",s);
    }

    @Test
    void testSetImage() {
        car.setImage("renault_2008.jpg");
        String s=car.getImage();
        assertEquals("set image errato","renault_2008.jpg",s);
    }
}