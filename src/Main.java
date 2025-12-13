import entity.Menu;
import entity.Product;
import entity.Stock;
import utils.Calculadora;
import utils.Printer;
import utils.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Hamburguesa", 500, LocalDateTime.now()));
        productList.add(new Product("Papas", 250, LocalDateTime.now()));
        productList.add(new Product("Gaseosa", 100, LocalDateTime.now()));
        productList.add(new Product("Pollo", 600, LocalDateTime.now()));
        productList.add(new Product("Helado", 150, LocalDateTime.now()));
        productList.add(new Product("Jugo", 100, LocalDateTime.now()));
        productList.add(new Product("Milanesa", 300, LocalDateTime.now()));
        productList.add(new Product("Galletas", 200, LocalDateTime.now()));
        productList.add(new Product("Agua", 120, LocalDateTime.now()));
        productList.add(new Product("Pancho", 180, LocalDateTime.now()));

        // PRELOAD STOCK
        Stock.preloadProducts(productList);

        Printer printer = new Printer();
        Calculadora calculadora = new Calculadora();
        Reader reader = new Reader();
        InventoryManager inventory = new InventoryManager();

        boolean appRunning = true;

        while (appRunning) {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar productos al kiosko");
            System.out.println("2. Venta de Productos (En construcción)");
            System.out.println("3. Pagar pedido (En construcción)");
            System.out.println("4. Mostrar productos (En construcción)");
            System.out.println("5. Mostrar ventas (En construcción)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    agregarProductosKiosko(reader);
                    break;

                case 2:
                    System.out.println("\n--- Venta (Mockup) ---");
                    break;

                case 3:
                    System.out.println("\n--- Pagar (Mockup) ---");
                    break;

                case 4:
                    mostrarProductosMenu(reader);
                    break;

                case 5:
                    System.out.println("\n--- Ventas (Mockup) ---");
                    break;

                case 6:
                    appRunning = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // ============================================================
    //  ADD PRODUCTS MENU
    // ============================================================

    private static void agregarProductosKiosko(Reader reader) {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n--- Agregar productos al kiosko ---");
            System.out.println("1. Nuevo producto");
            System.out.println("2. Producto existente (Ver Productos)");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    System.out.println("Ingrese nombre del producto:");
                    String name = reader.readString();

                    System.out.println("Ingrese precio:");
                    double price = reader.readDouble();

                    Product newProd = new Product(name, price, LocalDateTime.now());
                    Stock.registerProduct(newProd);

                    System.out.println("Producto registrado correctamente (sin stock).");
                    break;

                case 2:
                    Stock.showProductsWithoutStock();

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
    // 4. MOSTRAR PRODUCTOS (Semi-Working)
    // ==============================================================

    private static void mostrarProductosMenu(Reader reader) {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n---- Mostrar Productos ----");
            System.out.println("1. Ver Stock");
            System.out.println("2. Ver productos sin Stock");
            System.out.println("3. Agregar Stock al producto");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    Stock.showStock();
                    break;

                case 2:
                    Stock.showProductsWithoutStock();
                    break;

                case 3:
                    agregarStock(reader);
                    break;

                case 4:
                    submenu = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    //--------------- Add Stock Functionality ------------------//

    private static void agregarStock(Reader reader) {

        System.out.println("\n--- Agregar Stock ---");

        int index = 1;
        for (Product p : Stock.getRegisteredProducts()) {
            System.out.println(index + ". " + p.getName());
            index++;
        }

        System.out.print("Seleccione producto: ");
        int choice = reader.readInt();

        if (choice < 1 || choice > Stock.getRegisteredProducts().size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Product selected = Stock.getRegisteredProducts().get(choice - 1);

        System.out.print("Ingrese cantidad: ");
        int qty = reader.readInt();

        Stock.addStock(selected, qty);

        System.out.println("Stock agregado correctamente.");
    }

    // ==============================================================
    // 5. MOSTRAR VENTAS (Mockup only)
    // ==============================================================

    private static void mostrarVentasMockup() {
        System.out.println("\n--- Mostrar ventas (En construcción) ---");
        System.out.println("(Listado de ventas vendrá aquí)");
    }
}