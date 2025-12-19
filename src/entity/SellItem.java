package entity;

public class SellItem {

    private Product product;
    private int quantity;
    private double subtotal;

    public SellItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return product.getName() +
                " x" + quantity +
                " $" + subtotal;
    }
}