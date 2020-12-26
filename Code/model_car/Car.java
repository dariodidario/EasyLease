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
    private boolean visibility;
    private int doors;
    private String trasmision;
    private float avg_consumption;
    private int horse_power;
    private String emission_class;
    private int co2_emissions;
    private String power_supply;
    private int capacity;
    private String image_path;

    public Car(){}

    public Car(String id, String brand,String model, float price, String car_type, boolean visibility,int doors, String trasmission, float avg_consumption, int horse_power, String emission_class, int co2_emissions, String power_supply, int capacity, String image){
        setId(id);
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setType(car_type);
        setVisibility(visibility);
        setDoors(doors);
        setTrasmision(trasmission);
        setAvg_consumption(avg_consumption);
        setHorse_power(horse_power);
        setEmission_class(emission_class);
        setCo2_emissions(co2_emissions);
        setPowerSupply(power_supply);
        setCapacity(capacity);
        setImage(image);
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

    public int getDoors(){return this.doors;}

    public void setDoors(int doorsNew){this.doors=doorsNew;}

    public String getTrasmision(){return this.trasmision;}

    public void setTrasmision(String trasmisionNew){this.trasmision=trasmisionNew;}

    public float getAvg_consumption(){return this.avg_consumption;}

    public void setAvg_consumption(float avg_consumptionNew){this.avg_consumption=avg_consumptionNew;}

    public int getHorse_power(){return this.horse_power;}

    public void setHorse_power(int horse_powerNew){this.horse_power=horse_powerNew;}

    public String getEmission_class(){return this.emission_class;}

    public void setEmission_class(String emission_classNew){this.emission_class=emission_classNew;}

    public int getCo2_emissions(){return this.co2_emissions;}

    public void setCo2_emissions(int co2_emissioniNew){this.co2_emissions=co2_emissioniNew;}

    public int getCapacity(){return this.capacity;}

    public void setCapacity(int capacityNew){this.capacity=capacityNew;}

    public String getImage(){return this.image_path;}

    public void setImage(String imageNew){this.image_path=imageNew;}

}