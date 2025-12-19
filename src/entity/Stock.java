package entity;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Stock {

    private Product product;
    private int quantity;
    private LocalDateTime lastUpdate;

    // ===============================
    // STATIC STORAGE
    // ===============================

    private static List<Product> registeredProducts = new ArrayList<>();
    private static List<Stock> stockList = new ArrayList<>();

    // ===============================
    // CONSTRUCTOR
    // ===============================

    public Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.lastUpdate = LocalDateTime.now();
    }

    public static void preloadProducts(List<Product> products) {
        registeredProducts.addAll(products);
    }
    // ===============================
    // GETTERS
    // ===============================

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    // ===============================
    // CORE METHODS
    // ===============================

    public void addQuantity(int qty) {
        this.quantity += qty;
        this.lastUpdate = LocalDateTime.now();
    }

    public static void addRandomStockToAllProducts() {

        for (Product p : registeredProducts) {
            int randomQty = ThreadLocalRandom.current().nextInt(0, 7);

            if (randomQty > 0) {
                addStock(p, randomQty);
            }
        }

        System.out.println("Stock aleatorio agregado a los productos.");
    }

    public static List<Stock> getAvailableStock() {
        List<Stock> available = new ArrayList<>();
        for (Stock s : stockList) {
            if (s.quantity > 0) {
                available.add(s);
            }
        }
        return available;
    }

    public static boolean deductStock(Product product, int qty) {
        Stock s = findStockByProduct(product);
        if (s == null || s.quantity < qty) {
            return false;
        }
        s.quantity -= qty;
        return true;
    }

    // ===============================
    // STATIC HELPERS
    // ===============================

    public static void registerProduct(Product p) {
        registeredProducts.add(p);
    }

    public static List<Product> getRegisteredProducts() {
        return registeredProducts;
    }

    public static Stock findStockByProduct(Product p) {
        for (Stock s : stockList) {
            if (s.product.equals(p)) return s;
        }
        return null;
    }

    public static void addStock(Product p, int qty) {
        Stock s = findStockByProduct(p);
        if (s == null) {
            stockList.add(new Stock(p, qty));
        } else {
            s.addQuantity(qty);
        }
    }

    // ===============================
    // DISPLAY METHODS
    // ===============================

    public static void showStock() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n--- STOCK ---");
        for (Stock s : stockList) {
            if (s.quantity > 0) {
                System.out.println(
                        s.product.getName() +
                                " | Cantidad: " + s.quantity +
                                " | Fecha: " + s.lastUpdate.format(fmt)
                );
            }
        }
    }

    public static void showProductsWithoutStock() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n--- PRODUCTOS SIN STOCK ---");

        for (Product p : registeredProducts) {
            Stock s = findStockByProduct(p);

            if (s == null || s.quantity == 0) {
                System.out.println(
                        p.getName() +
                                " | $" + p.getPrice() +
                                " | " + p.getDate().format(fmt)
                );
            }
        }
    }
}
