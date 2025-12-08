package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {
    private String name;
    private int quantity;
    private double price;
    private LocalDateTime date;

    //private List <entidades.Product> products;
    public Product(String name, int quantity, double price, LocalDateTime date) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "entidades.Product [name=" + name +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date=" + date.format(formatter) + "]";
    }
}