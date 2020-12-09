package entities;

import repositories.ItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username, firstname, lastname;
    private int privilege; //0 - User, 1 - Staff, 2 - Admin
    private Cart cart;

    public User(String username, String firstname, String lastname, int privilege) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.privilege = privilege;
        cart = new Cart();
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPrivilege() {
        return privilege;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", privilege=" + privilege +
                '}';
    }

    public Cart getCart() {
        return cart;
    }
}
