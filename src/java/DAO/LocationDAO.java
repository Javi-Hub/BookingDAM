package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Location;
import utils.ConnectionFactory;
import utils.MotorMySQL;
import utils.MotorSQL;

public class LocationDAO implements IDAO<Location, Integer>{
    
    private MotorSQL motorSQL;
    private String SQL_FINDALL = 
            "SELECT * FROM `location` WHERE 1=1";
    
    public LocationDAO(){
        motorSQL = ConnectionFactory.selectDb();
    }
    
    @Override
    public int add(Location bean) {
        return 0;
    }

    @Override
    public int delete(Integer Integer) {
        return 0;
    }

    @Override
    public ArrayList<Location> findAll(Location bean) {
        ArrayList<Location> locations = new ArrayList<>();
        String sql = SQL_FINDALL;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                   sql += "AND id='" + bean.getId() + "'";
                }
                if (bean.getCity()!= null) {
                   sql += "AND city='" + bean.getCity()+ "'";
                }
             }
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                Location location = new Location();
                location.setId(resultset.getInt(1));
                location.setCity(resultset.getString(2));
              
                locations.add(location);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return locations;
    }

    @Override
    public int update(Location bean) {
        return 0;
    }
    
}
