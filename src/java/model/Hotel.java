package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Hotel {
    public int id, idLocation, bookedRooms, category;
    public String name, description, urlImage, city;
    private double rate, averagePrize;

    public Hotel(int id, String name, int category, String description, double rate, double averagePrize, String urlImage, int idLocation, String city, int bookedRooms) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.rate = rate;
        this.averagePrize = averagePrize;
        this.urlImage = urlImage;
        this.idLocation = idLocation;
        this.city = city;
        this.bookedRooms = bookedRooms;
     }
    
    public Hotel() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdLocation() {
        return idLocation;
    }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getAveragePrize() {
        return averagePrize;
    }
    public void setAveragePrize(double averagePrize) {
        this.averagePrize = averagePrize;
    }
    public int getBookedRooms() {
        return bookedRooms;
    }
    public void setBookedRooms(int bookedRooms) {
        this.bookedRooms = bookedRooms;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
   
    public static String toArrayJSon(ArrayList<Hotel> hotels){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(hotels);
        return resp;
    }
    
    public static String toObjectJson(Hotel hotel){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(hotel);
        return resp;
    }
    
}
