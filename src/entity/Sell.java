package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sell {
    private static final double IVA_PERCENT = 0.21;
    private static final double TIP_PERCENT = 0.1;

    private static List<Sell> sells = new ArrayList<>();
    private List<SellItem> items = new ArrayList<>();
    private LocalDateTime date;
    private double subtotal;
    private double iva;
    private double tip;
    private double total;

    public Sell() {
        this.date = LocalDateTime.now();
    }

    public void addItem(SellItem item) {
        items.add(item);
        subtotal += item.getSubtotal();
        recalcualte();
    }

    public void recalcualte() {
        iva = subtotal * IVA_PERCENT;
        tip = subtotal * TIP_PERCENT;
        total = subtotal + iva + tip;
    }

    public void register() {
        sells.add(this);
    }

    public static List<Sell> getSells() {
        return sells;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append("\n--- VENTA ---\n");
        for (SellItem item : items) {
            sb.append(item).append("\n");
        }
        sb.append("------------------\n");
        sb.append("Subtotal: ").append(subtotal).append("\n");
        sb.append("IVA: ").append(iva).append("\n");
        sb.append("Propina: ").append(tip).append("\n");
        sb.append("TOTAL: ").append(total).append("\n");
        sb.append("Fecha: ").append(date.format(fmt)).append("\n");

        return sb.toString();
    }

}
