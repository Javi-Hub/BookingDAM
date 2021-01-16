
package utils;

public class ConnectionFactory {
    public static final int db = 1;
    public static final int MYSQL_DB = 1;
    
    public static MotorSQL selectDb(){
        MotorSQL motorSQL = null;
        switch(db){
            case MYSQL_DB:
                motorSQL = new MotorMySQL();
                break;
        }
        return motorSQL;
    }
}
