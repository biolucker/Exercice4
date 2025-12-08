import java.time.LocalDateTime;

import entity.Menu;
import entity.Product;
import utils.Calculadora;
import utils.Printer;
import utils.Reader;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(1, "Hamburguesa", 500.0));
        menu.add(new Menu(2, "Papas", 250.0));
        menu.add(new Menu(3, "Gaseosa", 100.0));
        menu.add(new Menu(4, "Helado", 200.0));
        menu.add(new Menu(5, "Jugo", 100.0));
        menu.add(new Menu(6, "Pollo", 100.0));

        Printer printer = new Printer();
        Calculadora calculadora = new Calculadora();
        Reader reader = new Reader();

        // NUEVO — INVENTARIO
        InventoryManager inventoryManager = new InventoryManager();

        // ================================
        // PRE-PAYMENT MENU
        // ================================
        boolean preMenuActive = true;

        while (preMenuActive) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar producto a la tienda");
            System.out.println("2. Ver inventario");
            System.out.println("3. Continuar a comprar del menú");
            System.out.println("Seleccione una opción: ");

            int option = reader.readInt();

            switch (option) {
                case 1:
                    System.out.println("Ingrese nombre del producto:");
                    String name = reader.readString();

                    System.out.println("Ingrese cantidad:");
                    int qty = reader.readInt();

                    System.out.println("Ingrese precio:");
                    double price = reader.readDouble();

                    Product p = new Product(name, qty, price, LocalDateTime.now());
                    inventoryManager.addProduct(p);

                    System.out.println("Producto agregado exitosamente.");
                    break;

                case 2:
                    System.out.println("\n===== INVENTARIO =====");
                    if (inventoryManager.getInventory().isEmpty()) {
                        System.out.println("No hay productos almacenados.");
                    } else {
                        for (Product prod : inventoryManager.getInventory()) {
                            System.out.println(prod);
                        }
                    }
                    break;

                case 3:
                    preMenuActive = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        // ================================
        // EXISTING PURCHASE SYSTEM
        // ================================

        double ivaPorcentaje = 10.0;
        double propinaPorcentaje = 5.0;

        List<String> resumen = new ArrayList<>();
        double subtotal = 0.0;
        boolean continuar = true;

        while (continuar) {

            printer.printMenu(menu);
            System.out.println("Inserte la ID del producto que desea comprar: ");
            int id = reader.readInt();

            Menu seleccionado = null;
            for (Menu item : menu) {
                if (item.getId() == id) {
                    seleccionado = item;
                    break;
                }
            }

            if (seleccionado == null) {
                System.out.println("El producto seleccionado no existe.");
            } else {
                System.out.println("Inserte la cantidad: ");
                int cantidad = reader.readInt();
                double totalItem = calculadora.multiplicar(seleccionado.getPrecio(), cantidad);
                subtotal = calculadora.suma(subtotal, totalItem);

                resumen.add(seleccionado.getNombre() + " x" + cantidad + " = $" + totalItem);
            }

            System.out.println("¿Desea agregar otro producto? (s/n): ");
            String respuesta = reader.readString();
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }

        double iva = subtotal * (ivaPorcentaje / 100);
        double propina = subtotal * (propinaPorcentaje / 100);
        double totalFinal = subtotal + iva + propina;

        System.out.println("\n--- RESUMEN DE COMPRA ---");
        for (String linea : resumen) {
            System.out.println(linea);
        }

        System.out.println("----------------------------");
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA (" + ivaPorcentaje + "%): $" + iva);
        System.out.println("Propina (" + propinaPorcentaje + "%): $" + propina);
        printer.printTotal(totalFinal);
    }
}