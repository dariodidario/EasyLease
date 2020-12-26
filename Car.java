package com.easylease.EasyLease.model.car;

/* this class is used to create a new car
   the methods of this class are used to getter
   and setter the information about a car
* */

public class Car {
    private String id;
    private String brand;
    private String model;
    private float price;
    private String car_type;
    private String power_supply;
    private boolean visibility;

    public Car(){}

    public Car(String id, String brand,String model, float price, String car_type,String power_supply, boolean visibility){
        setId(id);
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setType(car_type);
        setPowerSupply(power_supply);
        setVisibility(visibility);
    }

    public String getId(){
        return this.id;
    }

    public void setId(String idNew){
        this.id=idNew;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String brandNew){
        this.brand=brandNew;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String modelNew){
        this.model=modelNew;
    }

    public float getPrice(){
        return this.price;
    }

    public void setPrice(float priceNew){
        this.price=priceNew;
    }

    public String getType(){
        return this.car_type;
    }

    public void setType(String car_typeNew){
        this.car_type=car_typeNew;
    }

    public String getPowerSupply(){
        return this.power_supply;
    }

    public void setPowerSupply(String powerSupplyNew){
        this.power_supply=powerSupplyNew;
    }

    public boolean getVisibility(){
        return this.visibility;
    }

    public void setVisibility(boolean visibilityNew){
        this.visibility=visibilityNew;
    }

}