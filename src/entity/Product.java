package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {
    private String name;
    private double price;
    private LocalDateTime date;

    //private List <Product> products;
    public Product(String name, double price, LocalDateTime date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.date = LocalDateTime.now();
    }

    public String getName() {
        return name;
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
        return "Product [Name = " + name +
                ", Price = $" + price +
                ", Date = " + date.format(formatter) + "]";
    }
}