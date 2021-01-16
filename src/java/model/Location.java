package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Location {
    
    private int id;
    private String city;

    public Location(int id, String city) {
        this.id = id;
        this.city = city;
    }
    public Location() {
    }
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public static String toArrayJSon(ArrayList<Location> locations){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(locations);
        return resp;
    }
    
    public static String toObjectJson(Location location){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(location);
        return resp;
    }
    
}
