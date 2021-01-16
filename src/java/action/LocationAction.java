package action;

import DAO.LocationDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Location;

public class LocationAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringDestiny = "";
        String action = (String) request.getParameter("ACTION");
        String [] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                stringDestiny = findAll(request, response);
                break;
        }
        return stringDestiny;
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response){
        LocationDAO locationDAO = new LocationDAO();
        ArrayList<Location> lstHotels = locationDAO.findAll(null);
        return Location.toArrayJSon(lstHotels);
    }
    
}
