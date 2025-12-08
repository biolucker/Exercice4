import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<Product> inventory = new ArrayList<>();

    public void addProduct(Product p) {
        inventory.add(p);
    }

    public List<Product> getInventory() {
        return inventory;
    }
}