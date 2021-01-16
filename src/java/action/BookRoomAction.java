package action;

import DAO.BookRoomDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BookRoom;

public class BookRoomAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringDestiny = "";
        String action = (String) request.getParameter("ACTION");
        String [] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                stringDestiny = findAll(request, response);
                break;
            case "INSERT":
                stringDestiny = insertSQL(request, response);
                break;
        }
        return stringDestiny;
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response){
        BookRoomDAO bookRoomDAO = new BookRoomDAO();
        ArrayList<BookRoom> lstBookRooms = bookRoomDAO.findAll(null);
        return BookRoom.toArrayJSon(lstBookRooms);
    } 

    private String insertSQL(HttpServletRequest request, HttpServletResponse response) {
        int id_user = Integer.parseInt(request.getParameter("USER"));
        int id_room = Integer.parseInt(request.getParameter("ROOM"));
        
        BookRoom bookRoom = new BookRoom();
        bookRoom.setIdUser(id_user);
        bookRoom.setIdRoom(id_room);
        bookRoom.setCost(45.00);
        BookRoomDAO bookRoomDAO = new BookRoomDAO();
        bookRoomDAO.add(bookRoom);
        return BookRoom.toObjectJson(bookRoom);
    }
}
