import java.util.List;

public class Printer {
    public void printMenu(List<Menu> menu) {
        System.out.println("------- Menú -------");
        for (Menu item : menu) {
            System.out.println(item);
        }
    }

    public void printTotal(double total) {
        System.out.println("Precio final: $" + total);
    }
}