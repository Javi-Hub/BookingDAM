package DAO;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;
import utils.ConnectionFactory;
import utils.MotorSQL;

public class UserDAO implements IDAO<User, Integer>{
    
    private MotorSQL motorSQL;
    private final String SQL_FINDALL = 
            "SELECT * FROM `user` WHERE 1=1";
  

    public UserDAO() {
        motorSQL = ConnectionFactory.selectDb();
    }

    @Override
    public int add(User bean) {
        return 0;
    }

    @Override
    public int delete(Integer Integer) {
        return 0;
    }

    @Override
    public ArrayList<User> findAll(User bean) {
        ArrayList<User> users = new ArrayList<>();
        String sql = SQL_FINDALL;
        
        try {
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                   sql += " AND id='" + bean.getId() + "'";
                }
                if (bean.getName() != null) {
                   sql += " AND name='" + bean.getName() + "'";
                }
                if (bean.getSurename()!= null) {
                   sql += " AND surname='" + bean.getSurename()+ "'";
                }
                if (bean.getEmail()!= null) {
                   sql += " AND email='" + bean.getEmail()+ "'";
                }
                if (bean.getPassword()!= null) {
                   sql += " AND password='" + bean.getPassword()+ "'";
                }
            }
            
            System.out.println(sql);
            ResultSet resultset = motorSQL.executeQuery(sql);
            
            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getInt(1));
                user.setName(resultset.getString(2));
                user.setSurename(resultset.getString(3));
                user.setEmail(resultset.getString(4));
                user.setPassword(resultset.getString(5));
                
                users.add(user);
            }
           
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally{
            motorSQL.disconnect();
        }
        return users;
    }

    @Override
    public int update(User bean) {
        return 0;
    }
    
    
}
