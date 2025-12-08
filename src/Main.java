import entity.Menu;
import entity.Product;
import utils.Calculadora;
import utils.Printer;
import utils.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(1, "Hamburguesa", 500.0));
        menu.add(new Menu(2, "Papas", 250.0));
        menu.add(new Menu(3, "Gaseosa", 100.0));
        menu.add(new Menu(4, "Helado", 200.0));
        menu.add(new Menu(5, "Jugo", 100.0));
        menu.add(new Menu(6, "Pollo", 100.0));

        /*
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));
        productList.add(new Product("Hamburguesa", 500  ));

         */

        Printer printer = new Printer();
        Calculadora calculadora = new Calculadora();
        Reader reader = new Reader();
        InventoryManager inventory = new InventoryManager();

        boolean appRunning = true;

        while (appRunning) {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar productos al kiosko");
            System.out.println("2. Venta de productos (En construcción)");
            System.out.println("3. Pagar pedido (En construcción)");
            System.out.println("4. Mostrar productos (En construcción)");
            System.out.println("5. Mostrar ventas (En construcción)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    agregarProductosKiosko(reader, inventory);
                    break;

                case 2:
                    ventaProductosMockup(menu, reader);
                    break;

                case 3:
                    pagarPedidoMockup();
                    break;

                case 4:
                    mostrarProductosMockup(inventory, reader);
                    break;

                case 5:
                    mostrarVentasMockup();
                    break;

                case 6:
                    appRunning = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // ==============================================================
    // 1. AGREGAR PRODUCTOS AL KIOSKO (WITH YOUR CURRENT FUNCTIONALITY)
    // ==============================================================

    private static void agregarProductosKiosko(Reader reader, InventoryManager inventory) {

        boolean submenu = true;
        while (submenu) {
            System.out.println("\n--- Agregar productos al kiosko ---");
            System.out.println("1. Nuevo");
            System.out.println("2. Existente");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            int op = reader.readInt();

            switch (op) {
                case 1: // NUEVO PRODUCTO (Already working)
                    System.out.println("Ingrese nombre del producto:");
                    String name = reader.readString();

                    System.out.println("Ingrese precio:");
                    double price = reader.readDouble();

                    Product p = new Product(name, price, LocalDateTime.now());
                    inventory.addProduct(p);

                    System.out.println("Producto agregado correctamente.");
                    break;

                case 2: // EXISTENTE (Mockup only)
                    System.out.println("\n--- Productos existentes ---");
                    if (inventory.getInventory().isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        int index = 1;
                        for (Product prod : inventory.getInventory()) {
                            System.out.println(index + ". " + prod);
                            index++;
                        }
                    }

                    System.out.println("\n*** En construcción *** (Aquí seleccionar producto y agregar cantidad)");
                    break;

                case 3:
                    submenu = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // ==============================================================
    // 2. VENTA DE PRODUCTOS (Mockup only)
    // ==============================================================

    private static void ventaProductosMockup(List<Menu> menu, Reader reader) {

        System.out.println("\n--- Venta de productos (En construcción) ---");
        System.out.println("Productos disponibles:");

        for (Menu m : menu) {
            System.out.println(m);
        }

        System.out.println("\n(Opción para pedir productos vendrá aquí)");
        System.out.println("(Opción para ingresar cantidad)");
        System.out.println("(Opción para añadir otro o terminar orden)\n");

        System.out.println("*** Funcionalidad en construcción ***");
    }

    // ==============================================================
    // 3. PAGAR PEDIDO (Mockup only)
    // ==============================================================

    private static void pagarPedidoMockup() {
        System.out.println("\n--- Pagar pedido (En construcción) ---");

        System.out.println("Resumen del pedido:");
        System.out.println("--------------------------");
        System.out.println("hamburguesas pollo x3 $1500");
        System.out.println("gaseosa cocacola  x3 $900");
        System.out.println("papas             x3 $450");
        System.out.println("--------------------------");
        System.out.println("Subtotal:");
        System.out.println("IVA:");
        System.out.println("Total:");
        System.out.println("--------------------------");

        System.out.println("Tipo de pago");
        System.out.println("* Efectivo");
        System.out.println("* Tarjeta débito");
        System.out.println("   1- VISA");
        System.out.println("   2- MASTER");
        System.out.println("* Mercado Pago");
        System.out.println("* Tarjeta crédito");
        System.out.println("   1- AMEX");
        System.out.println("   2- VISA");
        System.out.println("   3- MASTER");

        System.out.println("\n*** Funcionalidad en construcción ***");
    }

    // ==============================================================
    // 4. MOSTRAR PRODUCTOS (Mockup only)
    // ==============================================================

    private static void mostrarProductosMockup(InventoryManager inventory, Reader reader) {
        System.out.println("\n--- Mostrar productos ---");
        System.out.println("1. Productos registrados");
        System.out.println("2. Productos disponibles");
        System.out.println("3. Volver");

        System.out.println("\n*** En construcción ***");

        System.out.println("\nProductos registrados:");
        for (Product p : inventory.getInventory()) {
            System.out.println(p);
        }
    }

    // ==============================================================
    // 5. MOSTRAR VENTAS (Mockup only)
    // ==============================================================

    private static void mostrarVentasMockup() {
        System.out.println("\n--- Mostrar ventas (En construcción) ---");
        System.out.println("(Listado de ventas vendrá aquí)");
    }
}