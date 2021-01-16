package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BookRoom;
import utils.ConnectionFactory;
import utils.MotorSQL;

public class BookRoomDAO implements IDAO<BookRoom, Integer>{
    
    private MotorSQL motorSQL;
    private final String SQL_FINDALL = 
            "SELECT * FROM `book_room` WHERE 1=1";
    private final String SQL_INSERT =
            "INSERT INTO book_room (date_in, date_out, number_days, number_people, cost, id_user, id_room) VALUES ";
    
    public BookRoomDAO(){
        motorSQL = ConnectionFactory.selectDb();
    }
    
    @Override
    public int add(BookRoom bean) {
        int resp = 0;
        try {
            motorSQL.connect();
            String sql = SQL_INSERT + "(" + bean.getDateIn() + ", " + bean.getDateOut() + ", " + bean.getNumberDays()+ ", " + 
                    bean.getNumberPeople()+ ", " + bean.getCost() + ", " + bean.getIdUser()+ ", " + bean.getIdRoom()+ ")";
            
             //desactivo la restriccion de claves foráneas para insert
            motorSQL.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSQL.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            motorSQL.execute("SET FOREIGN_KEY_CHECKS=1;");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSQL.disconnect();
        }
        
        if (resp > 0) {
            System.out.println("Reserva insertada con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer Integer) {
        return 0;
    }

    @Override
    public ArrayList<BookRoom> findAll(BookRoom bean) {
        ArrayList<BookRoom> bookRooms = new ArrayList<>();
        String sql = SQL_FINDALL;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                   sql += "AND id='" + bean.getId() + "'";
                }
                if (bean.getDateIn()!= null) {
                   sql += "AND date_in='" + bean.getDateIn()+ "'";
                }
                if (bean.getDateOut()!= null) {
                   sql += "AND date_out='" + bean.getDateOut()+ "'";
                }
                if (bean.getNumberDays()!= 0) {
                   sql += "AND number_days='" + bean.getNumberDays()+ "'";
                }
                if (bean.getNumberPeople()!= 0) {
                   sql += "AND number_people='" + bean.getNumberPeople()+ "'";
                }
                if (bean.getCost()!= 0) {
                   sql += "AND cost='" + bean.getCost()+ "'";
                }
                if (bean.getIdUser()!= 0) {
                   sql += "AND id_user='" + bean.getIdUser()+ "'";
                }
                if (bean.getIdRoom()!= 0) {
                   sql += "AND id_room='" + bean.getIdRoom()+ "'";
                }
             }
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                BookRoom bookRoom = new BookRoom();
                bookRoom.setId(resultset.getInt(1));
                bookRoom.setDateIn(resultset.getDate(2));
                bookRoom.setDateOut(resultset.getDate(3));
                bookRoom.setNumberDays(resultset.getInt(4));
                bookRoom.setNumberPeople(resultset.getInt(5));
                bookRoom.setCost(resultset.getDouble(6));
                bookRoom.setIdUser(resultset.getInt(7));
                bookRoom.setIdRoom(resultset.getInt(8));
                
                bookRooms.add(bookRoom);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return bookRooms;
    }

    @Override
    public int update(BookRoom bean) {
        return 0;
    }
    
}
