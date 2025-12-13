package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {

    private String name;
    private double price;
    private int quantity;
    private LocalDateTime date;

    // Used for preloaded list in Main
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0; // default
        this.date = LocalDateTime.now();
    }

    // Used when user adds a fully defined product
    public Product(String name, double price, LocalDateTime date) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.date = date;
    }

    // Add stock
    public void addQuantity(int q) {
        this.quantity += q;
        this.date = LocalDateTime.now();
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return name + " | $" + price + " | Cant: " + quantity + " | Fecha: " + date.format(f);
    }
}