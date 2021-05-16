package action;

import DAO.HotelDAO;
import DAO.UserDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

public class UserAction implements interfaces.Action{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String stringDestiny = "";
        String action = (String) request.getParameter("ACTION");
        String [] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                stringDestiny = findAllData(request, response);
                break;
            case "VALIDATE":
                stringDestiny = findUser(request, response);
                break;
            case "INSERT":
                stringDestiny = insertUser(request, response);
        }
        return stringDestiny;
    }
    
    private String findAllData(HttpServletRequest request, HttpServletResponse response){
        UserDAO userDAO = new UserDAO();
        ArrayList<User> lstUsers = userDAO.findAll(null);
        return User.toArrayJSon(lstUsers);
    }

    private String findUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        
        UserDAO userDAO = new UserDAO();
        ArrayList<User> lstUser = userDAO.findAll(user);
        return User.toArrayJSon(lstUser);
       
    }
  
    
    private String insertUser(HttpServletRequest request, HttpServletResponse response){
          String name = request.getParameter("NAME");
          String surename = request.getParameter("SURENAME");
          String email = request.getParameter("EMAIL");
          String password = request.getParameter("PASSWORD");
          
          User user = new User();
          user.setName(name);
          user.setSurename(surename);
          user.setEmail(email);
          user.setPassword(password);
          
          UserDAO userDAO = new UserDAO();
          userDAO.add(user);
          User myUser = userDAO.findOneUser(user);
          return User.toObjectJson(myUser);
             
          
          
    }

}
