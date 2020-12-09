package repositories;

import entities.User;

import java.sql.*;

public class AccountRepository {
    public static boolean authenticate(String username, String password) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "select * from account where username=? and password=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return false;
    }

    public static User getUser(String username) throws SQLException {
        User user = null;
        Connection connection = DatabaseManager.getConnection();
        String query = "select * from account where username=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet.getString("username"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getInt("privilege"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return user;
    }
}
