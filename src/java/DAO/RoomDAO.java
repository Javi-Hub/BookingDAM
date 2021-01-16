package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Room;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author Javier
 */
public class RoomDAO implements IDAO<Room, Integer>{
    
    private MotorSQL motorSQL;
    private final String SQL_FINDALL = 
            "SELECT * FROM `room` WHERE 1=1";
    private final String SQL_FINDFILTER = "SELECT room.*, hotel.name, location.city FROM `room` INNER JOIN hotel ON room.id_hotel = hotel.id "
            + "INNER JOIN `location` ON location.id = hotel.id_location WHERE 1=1 ";

    public RoomDAO() {
        motorSQL = ConnectionFactory.selectDb();
    }

    @Override
    public int add(Room bean) {
        return 0;
    }

    @Override
    public int delete(Integer Integer) {
        return 0;
    }

    @Override
    public ArrayList<Room> findAll(Room bean) {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql = SQL_FINDALL;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                   sql += "AND id='" + bean.getId() + "'";
                }
                if (bean.getCapacity()!= 0) {
                   sql += "AND capacity='" + bean.getCapacity()+ "'";
                }
                if (bean.getCost()!= 0) {
                   sql += "AND cost='" + bean.getCost()+ "'";
                }
                if (bean.getAvailable()!= null) {
                   sql += "AND available='" + bean.getAvailable()+ "'";
                }
                if (bean.getIdHotel()!= 0) {
                   sql += "AND id_hotel='" + bean.getIdHotel()+ "'";
                }
             }
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Room room = new Room();
                room.setId(resultset.getInt(1));
                room.setCapacity(resultset.getInt(2));
                room.setCost(resultset.getDouble(3));
                room.setAvailable(resultset.getString(4));
                room.setIdHotel(resultset.getInt(5));
                
                rooms.add(room);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return rooms;
    }
    
    public ArrayList<Room> findFilter(Room bean) {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql = SQL_FINDFILTER;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getHotelName()!= null) {
                   sql += "AND hotel.name='" + bean.getHotelName()+ "'";
                }
             }
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Room room = new Room();
                room.setId(resultset.getInt(1));
                room.setCapacity(resultset.getInt(2));
                room.setCost(resultset.getDouble(3));
                room.setAvailable(resultset.getString(4));
                room.setIdHotel(resultset.getInt(5));
                room.setHotelName(resultset.getString(6));
                room.setCity(resultset.getString(7));
               
                rooms.add(room);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return rooms;
    }

    @Override
    public int update(Room bean) {
        return 0;
    }
    
    
}
