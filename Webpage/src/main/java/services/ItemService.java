package services;


import entities.Category;
import entities.Item;
import entities.User;
import repositories.ItemRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemService {

    public static ArrayList<Item> getAllItems(){
        ArrayList<Category> categories = CategoryService.getCategories();
        ArrayList<Item> items = new ArrayList<>();
        for(Category category : categories){
            items.addAll(getItems(category.getName()));
        }
        return items;
    }

    public static ArrayList<Item> getItems(String categoryName){
        try {
            return ItemRepository.getItems(categoryName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Item getItem(int id){
        try {
            return ItemRepository.getItem(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void removeItem(int id){
        try {
            ItemRepository.deleteItem(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateItem(Item item){
        try {
            ItemRepository.updateItem(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String payment(User user){
        try {
            return ItemRepository.payment(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Payment Error, please try again!";
    }
}
