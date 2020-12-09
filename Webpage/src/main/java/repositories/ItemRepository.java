package repositories;

import entities.Category;
import entities.Item;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRepository {

    public static ArrayList<Item> getItems(String category) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT * FROM item, category WHERE item.category = category.id AND category.name=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                items.add(new Item(resultSet.getInt("item.id"), resultSet.getString("item.name"),
                        resultSet.getInt("item.price"), resultSet.getInt("item.quantity"),
                        new Category(resultSet.getInt("category.id"), resultSet.getString("category.name"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return items;
    }

    public static Item getItem(int id) throws SQLException {
        Item item = null;
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT * FROM item ,category WHERE item.category = category.id AND item.id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                item = new Item(resultSet.getInt("item.id"), resultSet.getString("item.name"),
                        resultSet.getInt("item.price"), resultSet.getInt("item.quantity"),
                        new Category(resultSet.getInt("category.id"), resultSet.getString("category.name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return item;
    }

    public static void deleteItem(int id) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "DELETE FROM item WHERE item.id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public static void updateItem(Item item) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "UPDATE item SET name=?, price=?, quantity=? WHERE item.id =?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getQuantity());
            statement.setInt(4, item.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public static String payment(User user) throws SQLException {
        String returnString = "Payment Successful!";
        ArrayList<Item> items = user.getCart().getItems();
        Connection connection = DatabaseManager.getConnection();
        try{
            connection.setAutoCommit(false);
            for(Item item : items){
                Item dbItem = getItem(item.getId());
                if(dbItem.getQuantity() - item.getQuantity() < 0){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return "Payment unsuccessful, " + item.getName() + " is out of stock!";
                }
                dbItem.setQuantity(dbItem.getQuantity() - item.getQuantity());
                updateItem(dbItem);
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            returnString = "An error occurred and the payment has been cancelled, please try Again!";
            throw e;
        }finally {
            connection.setAutoCommit(true);
        }

        return returnString;
    }
}
