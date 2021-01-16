package action;

import DAO.HotelDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hotel;

public class HotelAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringDestiny = "";
        String action = (String) request.getParameter("ACTION");
        String [] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                stringDestiny = findAllData(request, response);
                break;
            case "FIND_FILTER":
                stringDestiny = findFilter(request, response);
                break;  
        }
        return stringDestiny;
    }
    
    private String findAllData(HttpServletRequest request, HttpServletResponse response){
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> lstHotels = hotelDAO.findAll();
        return Hotel.toArrayJSon(lstHotels);
    }

    private String findFilter(HttpServletRequest request, HttpServletResponse response) {
        String city = request.getParameter("CITY");
        Hotel hotel = new Hotel();
        hotel.setCity(city);
    
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> lstHotels = hotelDAO.findAll(hotel);
        return Hotel.toArrayJSon(lstHotels);

    }
    
    
    
}
