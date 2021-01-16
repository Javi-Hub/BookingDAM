package action;

import DAO.HotelDAO;
import DAO.RoomDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

public class RoomAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringDestiny = "";
        String action = (String) request.getParameter("ACTION");
        String [] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                stringDestiny = findAll(request, response);
                break;
            case "FIND_FILTER":
                stringDestiny = findFilter(request, response);
                break;
        }
        return stringDestiny; 
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response){
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> lstRooms = roomDAO.findAll(null);
        return Room.toArrayJSon(lstRooms);
    }

    private String findFilter(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("NAME_HOTEL");
        Room room = new Room();
        room.setHotelName(name);
        
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> lstRooms = roomDAO.findFilter(room);
        return Room.toArrayJSon(lstRooms);
    }
}
