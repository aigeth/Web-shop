package entities;

import repositories.ItemRepository;
import services.ItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private HashMap<Integer, Item> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public void add(Item cartItem){

        if(cartItems.containsKey(cartItem.getId())){
            Item item = cartItems.get(cartItem.getId());
            item.setQuantity(item.getQuantity() + 1);
            return;
        }
        cartItem.setQuantity(1);
        cartItems.put(cartItem.getId(), cartItem);
    }

    public void removeFromCart(int id){
        Item item = cartItems.get(id);
        if(item != null){
            if(item.getQuantity() > 1){
                item.setQuantity(item.getQuantity() - 1);
            }else{
                cartItems.remove(id);
            }
        }
    }

    public Item getItem(int id){
        return cartItems.get(id);
    }

    public ArrayList<Item> getItems(){
        return new ArrayList<Item>(cartItems.values());
    }

    public int getSum(){
        int sum = 0;
        for(Map.Entry<Integer, Item> entry: cartItems.entrySet()){
            Item item = entry.getValue();
            sum+= item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    public void clear(){
        cartItems.clear();
    }

    public int size(){
        return cartItems.size();
    }
}
