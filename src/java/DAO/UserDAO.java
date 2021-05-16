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
    private final String SQL_INSERT = 
            "INSERT INTO user (name, surename, email, password) VALUES ";
    private final String SQL_FIND_USER
            = "SELECT * FROM `user` WHERE 1=1 ";
    
    public UserDAO() {
        motorSQL = ConnectionFactory.selectDb();
    }

    @Override
    public int add(User bean) {
        int resp = 0;
        try {
            motorSQL.connect();
            String sql = SQL_INSERT + "('" + bean.getName() + "', '" + bean.getSurename() +
                    "', '" + bean.getEmail() + "', '" + bean.getPassword() + "')";
            
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
            System.out.println("Usuario registro con éxito");
        }
        
        return resp;
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
                   sql += " AND surename='" + bean.getSurename()+ "'";
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
    
    public User findOneUser(User bean) {
        User user = new User();
        String sql= SQL_FIND_USER;
        try {
            //1º) 
            motorSQL.connect();
            if (bean != null) {
                if (bean.getId() != 0) {
                    sql += " AND id='" + bean.getId()+ "'";
                }
                if (bean.getName() != null) {
                    sql += " AND name='" + bean.getName() + "'";
                }
                if (bean.getSurename() != null) {
                    sql += " AND surename='" + bean.getSurename() + "'";
                }
                if (bean.getEmail() != null) {
                    sql += " AND email='" + bean.getEmail() + "'";
                }
                if (bean.getPassword() != null) {
                    sql += " AND password='" + bean.getPassword() + "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSQL.
                    executeQuery(sql);

            while (rs.next()) {
                
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurename(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));          
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSQL.disconnect();
        }
        return user;
    }

    @Override
    public int update(User bean) {
        return 0;
    }
    
    
}
