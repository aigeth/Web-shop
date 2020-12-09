package services;

import entities.Category;
import repositories.CategoryRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService {

    public static ArrayList<Category> getCategories(){
        try {
            return CategoryRepository.getCategories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Category getItem(int id){
        try {
            return CategoryRepository.getCategory(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void removeCategory(int id){
        try {
            CategoryRepository.deleteCategory(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateCategory(Category category){
        try {
            CategoryRepository.updateCategory(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
