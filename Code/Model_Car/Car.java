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
    private String changing;
    private double average_consumption;
    private int Horses;
    private String CO2;
    private double emissions;
    private String power_supply;
    private double capacity;
    private String optional_serie;
    private String image;

    public Car(){}

    public Car(String id, String brand,String model, float price, String car_type, boolean visibility,int doors, String changing, double average_consumption, int horses, String CO2, double emissions, String power_supply, double capacity, String optional_serie, String image){
        setId(id);
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setType(car_type);
        setVisibility(visibility);
        setDoors(doors);
        setChanging(changing);
        setAverage_consumption(average_consumption);
        setHorses(horses);
        setCO2(CO2);
        setEmissions(emissions);
        setPowerSupply(power_supply);
        setCapacity(capacity);
        setOptional_serie(optional_serie);
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

    public String getChanging(){return this.changing;}

    public void setChanging(String cambioNew){this.changing=cambioNew;}

    public double getAverage_consumption(){return this.average_consumption;}

    public void setAverage_consumption(double consumo_medioNew){this.average_consumption=consumo_medioNew;}

    public int getHorses(){return this.Horses;}

    public void setHorses(int cavalliNew){this.Horses=cavalliNew;}

    public String getCO2(){return this.CO2;}

    public void setCO2(String CO2New){this.CO2=CO2New;}

    public double getEmissions(){return this.emissions;}

    public void setEmissions(double emissioniNew){this.emissions=emissioniNew;}

    public double getCapacity(){return this.capacity;}

    public void setCapacity(double cilindrataNew){this.capacity=cilindrataNew;}

    public String getOptional_serie(){return this.optional_serie;}

    public void setOptional_serie(String optional_serieNew){this.optional_serie=optional_serieNew;}

    public String getImage(){return this.image;}

    public void setImage(String imageNew){this.image=imageNew;}

}