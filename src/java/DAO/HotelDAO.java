
package DAO;

import interfaces.IDAO;
import java.util.ArrayList;
import model.Hotel;
import utils.MotorSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionFactory;

public class HotelDAO implements IDAO<Hotel, Integer>{
    
    private MotorSQL motorSQL;
    /*private final String SQL_FINDALL = 
            "SELECT * FROM `hotel` WHERE 1=1";*/
    /*private final String SQL_FINDALL =
            "SELECT hotel.*, location.city FROM hotel INNER JOIN location ON hotel.id_location = location.id WHERE 1=1";*/
    private final String SQL_DELETE = "DELETE FROM hotel WHERE id=";
    private final String SQL_FINDALL ="SELECT hotel.*, location.city, COUNT(book_room.id) "
            + "FROM book_room INNER JOIN room ON room.id = book_room.id_room RIGHT JOIN "
            + "hotel ON room.id_hotel = hotel.id INNER JOIN location ON hotel.id_location = location.id GROUP BY hotel.id";
    private final String SQL_FIND_FILTER = "SELECT hotel.*, location.city, COUNT(book_room.id) "
            + "FROM book_room INNER JOIN room ON room.id = book_room.id_room RIGHT JOIN "
            + "hotel ON room.id_hotel = hotel.id INNER JOIN location ON hotel.id_location = location.id WHERE 1=1 ";
    private final String SQL_TOPTEN ="SELECT hotel.*, location.city, COUNT(book_room.id) "
            + "FROM book_room INNER JOIN room ON room.id = book_room.id_room RIGHT JOIN "
            + "hotel ON room.id_hotel = hotel.id INNER JOIN location ON hotel.id_location = location.id GROUP BY hotel.id"
            + " ORDER BY COUNT(book_room.id) DESC LIMIT 10";
    
    public HotelDAO(){
        motorSQL = ConnectionFactory.selectDb();
    }
    
    @Override
    public int add(Hotel bean) {
        return  0;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        motorSQL.connect();
        try {
            String sql = SQL_DELETE + id;
            //desactivo la restriccion de claves foráneas para borrado
            motorSQL.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSQL.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            motorSQL.execute("SET FOREIGN_KEY_CHECKS=1;");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSQL.disconnect();

        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }
    
    @Override
    public ArrayList<Hotel> findAll(Hotel bean) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String sql = SQL_FIND_FILTER;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                   sql += "AND id='" + bean.getId() + "'";
                }
                if (bean.getName() != null) {
                   sql += "AND name='" + bean.getName() + "'";
                }
                if (bean.getCategory()!= 0) {
                   sql += "AND category='" + bean.getCategory()+ "'";
                }
                if (bean.getDescription()!= null) {
                   sql += "AND description='" + bean.getDescription()+ "'";
                }
                if (bean.getRate()!= 0) {
                   sql += "AND rate='" + bean.getRate()+ "'";
                }
                if (bean.getAveragePrize()!= 0) {
                   sql += "AND average_prize='" + bean.getAveragePrize()+ "'";
                }                
                if (bean.getUrlImage() != null) {
                   sql += "AND url_image='" + bean.getUrlImage()+ "'";
                }
                if (bean.getIdLocation() != 0) {
                   sql += "AND id_location='" + bean.getIdLocation()+ "'";
                }
                if (bean.getCity()!= null) {
                   sql += "AND UPPER(city)='" + bean.getCity()+ "'";
                }
                if (bean.getBookedRooms()!= 0) {
                   sql += " AND COUNT(book_room.id)='" + bean.getBookedRooms()+ "'";
                }
              }
             sql += " GROUP BY hotel.id";
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultset.getInt(1));
                hotel.setName(resultset.getString(2));
                hotel.setCategory(resultset.getInt(3));
                hotel.setDescription(resultset.getString(4));
                hotel.setRate(resultset.getDouble(5));
                hotel.setAveragePrize(resultset.getDouble(6));
                hotel.setUrlImage(resultset.getString(7));
                hotel.setIdLocation(resultset.getInt(8));
                hotel.setCity(resultset.getString(9));
                hotel.setBookedRooms(resultset.getInt(10));
                
                hotels.add(hotel);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return hotels;
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String sql = SQL_FINDALL;
        
        try {
            motorSQL.connect();
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Hotel hotel = new Hotel();
                
                hotel.setId(resultset.getInt(1));
                hotel.setName(resultset.getString(2));
                hotel.setCategory(resultset.getInt(3));
                hotel.setDescription(resultset.getString(4));
                hotel.setRate(resultset.getDouble(5));
                hotel.setAveragePrize(resultset.getDouble(6));
                hotel.setUrlImage(resultset.getString(7));
                hotel.setIdLocation(resultset.getInt(8));
                hotel.setCity(resultset.getString(9));
                hotel.setBookedRooms(resultset.getInt(10));
                
                hotels.add(hotel);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return hotels;
    }
    
    public ArrayList<Hotel> findTopTen() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String sql = SQL_TOPTEN;
        
        try {
            motorSQL.connect();
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Hotel hotel = new Hotel();
                
                hotel.setId(resultset.getInt(1));
                hotel.setName(resultset.getString(2));
                hotel.setCategory(resultset.getInt(3));
                hotel.setDescription(resultset.getString(4));
                hotel.setRate(resultset.getDouble(5));
                hotel.setAveragePrize(resultset.getDouble(6));
                hotel.setUrlImage(resultset.getString(7));
                hotel.setIdLocation(resultset.getInt(8));
                hotel.setCity(resultset.getString(9));
                hotel.setBookedRooms(resultset.getInt(10));
                
                hotels.add(hotel);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return hotels;
    }

    @Override
    public int update(Hotel bean) {
        return 0;
    }
    
}
