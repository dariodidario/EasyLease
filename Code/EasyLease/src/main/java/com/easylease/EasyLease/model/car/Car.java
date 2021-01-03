package com.easylease.EasyLease.model.car;

/**
 * This class provides the information about the car of the EasyLease platform,
 *
 * @since 0.1
 * @author Michele Attilio Iodice
 * @version 0.2
 */

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
    private int cc;
    private String image_path;

    /**
     * This is the Empty constructor for the Client object.
     */
    public Car(){}
    /**
     * This is the Constructor of the Client Object.
     *
     * @param id the id  of the car
     * @param brand the brand  of the car
     * @param model the model of the car
     * @param price the price of the car
     * @param car_type the typology of the car
     * @param visibility the visibility of the car into the catalogue
     * @param doors the number of the car doors
     * @param trasmission the type of car trasmission
     * @param avg_consumption the average consumption of the car
     * @param horse_power the horse power of the car
     * @param emission_class the CO2 emission class of the car
     * @param co2_emissions the quantity of CO2 emissions  of the car
     * @param power_supply the type of car's power
     * @param cc the capacity of the car
     * @param image the path of car's image
     */
    public Car(String id, String brand,String model, float price, String car_type, boolean visibility,int doors, String trasmission, float avg_consumption, int horse_power, String emission_class, int co2_emissions, String power_supply, int cc, String image){
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
        setCc(cc);
        setImage(image);
    }

    /**
     * Returns the id of the car.
     *
     * @return the id of the car.
     */
    public String getId(){
        return this.id;
    }

    /**
     * Set the id of the car.
     *
     * @param idNew the new id of the car.
     */
    public void setId(String idNew){
        this.id=idNew;
    }

    /**
     * Returns the Brand of the car.
     *
     * @return the brand of the car.
     */
    public String getBrand(){
        return this.brand;
    }

    /**
     * Set the brand of the car.
     *
     * @param brandNew the new id of the car.
     */
    public void setBrand(String brandNew){
        this.brand=brandNew;
    }

    /**
     * Returns the model of the car.
     *
     * @return the model of the car.
     */
    public String getModel(){
        return this.model;
    }

    /**
     * Set the model of the car.
     *
     * @param modelNew the new model of the car.
     */
    public void setModel(String modelNew){
        this.model=modelNew;
    }

    /**
     * Returns the price of the car.
     *
     * @return the price of the car.
     */
    public float getPrice(){
        return this.price;
    }

    /**
     * Set the price of the car.
     *
     * @param priceNew the new price of the car.
     */
    public void setPrice(float priceNew){
        this.price=priceNew;
    }

    /**
     * Returns the typology of the car.
     *
     * @return the type of the car.
     */
    public String getType(){
        return this.car_type;
    }

    /**
     * Set the typology of the car.
     *
     * @param car_typeNew the new type of the car.
     */
    public void setType(String car_typeNew){
        this.car_type=car_typeNew;
    }

    /**
     * Returns the type of the car's power.
     *
     * @return the type of the car's power.
     */
    public String getPowerSupply(){
        return this.power_supply;
    }

    /**
     * Set the power of the car.
     *
     * @param powerSupplyNew the new power supply of the car.
     */
    public void setPowerSupply(String powerSupplyNew){
        this.power_supply=powerSupplyNew;
    }

    /**
     * Returns the visibility of the car into the catalogue.
     *
     * @return the visibility of the car.
     */
    public boolean getVisibility(){
        return this.visibility;
    }

    /**
     * Set the visibility of the car.
     *
     * @param visibilityNew the new visibility of the car.
     */
    public void setVisibility(boolean visibilityNew){
        this.visibility=visibilityNew;
    }

    /**
     * Returns the number of the car doors.
     *
     * @return the number of the car doors.
     */
    public int getDoors(){return this.doors;}

    /**
     * Set the number  of the car doors.
     *
     * @param doorsNew the new number of the car doors.
     */
    public void setDoors(int doorsNew){this.doors=doorsNew;}

    /**
     * Returns the type of the car trasmission.
     *
     * @return the type of the car trasmission.
     */
    public String getTrasmision(){return this.trasmision;}

    /**
     * Set the type  of the car trasmission.
     *
     * @param trasmisionNew the new trasmission of the car.
     */
    public void setTrasmision(String trasmisionNew){this.trasmision=trasmisionNew;}

    /**
     * Returns the average consumption of the car.
     *
     * @return the average consumption of the car.
     */
    public float getAvg_consumption(){return this.avg_consumption;}

    /**
     * Set the average consumption of the car.
     *
     * @param avg_consumptionNew the new average consumption of the car.
     */
    public void setAvg_consumption(float avg_consumptionNew){this.avg_consumption=avg_consumptionNew;}

    /**
     * Returns the horse power of the car.
     *
     * @return the horse power of the car.
     */
    public int getHorse_power(){return this.horse_power;}

    /**
     * Set the horse power of the car.
     *
     * @param horse_powerNew the new horse power of the car.
     */
    public void setHorse_power(int horse_powerNew){this.horse_power=horse_powerNew;}

    /**
     * Returns the emission class of the car.
     *
     * @return the emission class of the car.
     */
    public String getEmission_class(){return this.emission_class;}

    /**
     * Set the emission class of the car.
     *
     * @param emission_classNew the new emission class of the car.
     */
    public void setEmission_class(String emission_classNew){this.emission_class=emission_classNew;}

    /**
     * Returns the quantity of CO2 emissions of the car.
     *
     * @return the quantity of CO2 emissions of the car.
     */
    public int getCo2_emissions(){return this.co2_emissions;}

    /**
     * Set the quantity of CO2 emissions of the car.
     *
     * @param co2_emissionsNew the new quantity of CO2 emissions of the car.
     */
    public void setCo2_emissions(int co2_emissionsNew){this.co2_emissions=co2_emissionsNew;}

    /**
     * Returns the capacity of the car.
     *
     * @return the capacity of the car.
     */
    public int getCapacity(){return this.cc;}

    /**
     * Set the capacity of the car.
     *
     * @param ccNew the new capacity of the car.
     */
    public void setCc(int ccNew){this.cc=ccNew;}

    /**
     * Returns the path of the car image.
     *
     * @return the path of the car image.
     */
    public String getImage(){return this.image_path;}

    /**
     * Set the path of the car image.
     *
     * @param imageNew the new path of the car image.
     */
    public void setImage(String imageNew){this.image_path=imageNew;}

}
