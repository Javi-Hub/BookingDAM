package action;

import DAO.BookRoomDAO;
import java.util.ArrayList;
import java.util.Date;
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
        String dateIn = request.getParameter("DATE_IN");
        String dateOut = request.getParameter("DATE_OUT");
        int numDays = Integer.parseInt(request.getParameter("NUM_DAYS"));
        int numPeople = Integer.parseInt(request.getParameter("NUM_PEOPLE"));
        double cost = Double.parseDouble(request.getParameter("COST"));
        //ArrayList<BookRoom> lstBookRooms;
        BookRoom bookRoom = new BookRoom();
        bookRoom.setIdUser(id_user);
        bookRoom.setIdRoom(id_room);
        bookRoom.setDateIn(dateIn);
        bookRoom.setDateOut(dateOut);
        bookRoom.setNumberDays(numDays);
        bookRoom.setNumberPeople(numPeople);
        bookRoom.setCost(cost);
        BookRoomDAO bookRoomDAO = new BookRoomDAO();
        bookRoomDAO.add(bookRoom);
        //bookRoomDAO.findAll(bookRoom);
        //lstBookRooms = bookRoomDAO.findAll(bookRoom);
        return BookRoom.toObjectJson(bookRoom);    
    }
}
