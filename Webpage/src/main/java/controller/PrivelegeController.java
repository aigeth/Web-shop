package controller;

import entities.User;

import javax.servlet.http.HttpSession;

public class PrivelegeController {

    public static boolean isUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            return true;
        }
        return false;
    }

    public static boolean isStaff(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            if(user.getPrivilege() <= 1)
                return true;
        }
        return false;
    }

    public static boolean isAdmin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            if(user.getPrivilege() <= 2)
                return true;
        }
        return false;
    }
}
