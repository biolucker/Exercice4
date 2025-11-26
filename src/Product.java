import java.time.LocalDateTime;


public class Product {
    private String name;
    private int quantity;
    private double price;
    private LocalDateTime date;
    //private List <Product> products;
    public Product(String name, int quantity, double price, LocalDateTime date) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getName() {return name;}
    public int getQuantity() {return quantity;}
    public double getPrice() {return price;}
    public LocalDateTime getDate() {return date;}

    @Override
    public String toString() {
        return "Product [name=" + name + ", quantity=" + quantity + ", price=" + price + ", date=" + date;
    }
    public void setDateTimeNow(){
        date = LocalDateTime.now();
    }
}
