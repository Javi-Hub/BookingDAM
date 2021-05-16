package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

public class BookRoom {
    
    private int id, numberDays, numberPeople, idUser, idRoom;
    private String dateIn, dateOut;
    private double cost;

    public BookRoom(int id, int numberDays, int numberPeople, int idUser, int idRoom, String dateIn, String dateOut, double cost) {
        this.id = id;
        this.numberDays = numberDays;
        this.numberPeople = numberPeople;
        this.idUser = idUser;
        this.idRoom = idRoom;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.cost = cost;
    }

    public BookRoom() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumberDays() {
        return numberDays;
    }
    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }
    public int getNumberPeople() {
        return numberPeople;
    }
    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public String getDateIn() {
        return dateIn;
    }
    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }
    public String getDateOut() {
        return dateOut;
    }
    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
    public static String toArrayJSon(ArrayList<BookRoom> bookRooms){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(bookRooms);
        return resp;
    }
    
    public static String toObjectJson(BookRoom bookRoom){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(bookRoom);
        return resp;
    }
    
  
}
