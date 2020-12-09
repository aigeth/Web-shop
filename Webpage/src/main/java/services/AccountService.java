package services;

import entities.User;
import repositories.AccountRepository;

import java.sql.SQLException;

public class AccountService {

    public static boolean authenticate(String username, String password){
        try {
            return AccountRepository.authenticate(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static User getUser(String username){
        try {
            return AccountRepository.getUser(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
