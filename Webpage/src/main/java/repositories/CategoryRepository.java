package repositories;

import entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryRepository {

    public static Category getCategory(int id) throws SQLException {
        Category category = null;
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT * FROM category WHERE category.id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                category = new Category(resultSet.getInt("category.id"), resultSet.getString("category.name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return category;
    }

    public static void deleteCategory(int id) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "DELETE FROM category WHERE category.id=?";
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

    public static void updateCategory(Category category) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "UPDATE category SET name=? WHERE category.id =?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public static ArrayList<Category> getCategories() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT * FROM category";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                categories.add(new Category(resultSet.getInt("category.id"), resultSet.getString("category.name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }

        return categories;
    }
}
