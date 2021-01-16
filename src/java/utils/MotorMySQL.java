package utils;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MotorMySQL extends MotorSQL {

    /*Atributos para hablar con la BBDD*/
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;
    //Base de datos con la voy a conectar
    private static final String URL = "jdbc:mysql://localhost/booking";
    private static final String CONTROLLER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void connect() {
        try {
            //Controlador
            Class.forName(CONTROLLER);
            //Usuario y Contraseña para acceder a la BBDD
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //Creo un Statement con el objeto connection
            statement = connection.createStatement();
            System.out.println("Conectado");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //Consultas DDL
    @Override
    public int execute(String sql) {
        int resp = 0;
        try {
            resp = statement.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return resp;
    }

    //Consultas DML
    @Override
    public ResultSet executeQuery(String sql) {
        try {
            resultset = statement.executeQuery(sql);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return resultset;
    }

    @Override
    public void disconnect() {
        try {
            if (resultset != null) {
                 resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

}
